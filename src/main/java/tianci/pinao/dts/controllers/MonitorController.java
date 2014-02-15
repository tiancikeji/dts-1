package tianci.pinao.dts.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.AreaChannel;
import tianci.pinao.dts.services.AreaService;
import tianci.pinao.dts.services.CableService;
import tianci.pinao.dts.services.ChannelService;
import tianci.pinao.dts.tasks.service.TemTaskService;
import tianci.pinao.dts.util.PinaoConstants;

@Controller
public class MonitorController {
	
	@Autowired
	CableService cableService;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private TemTaskService temTaskService;
	
	@RequestMapping(value="/monitor" , method = RequestMethod.GET)
	public String index(Model model){
		return "monitor/index";
	}
	
	
	@RequestMapping(value="/monitor/{area_id}" , method = RequestMethod.GET)
	public String findByAreaId(HttpServletRequest request,@PathVariable int area_id,Model model){
		Area area = areaService.findById(area_id);
		if(area != null){
			/*List<Integer> ids = new ArrayList<Integer>();
			ids.add(area.getId());
			Map<Integer, List<AreaChannel>> result = channelService.findByAreaIds(ids);
			if(result != null){
				List<AreaChannel> channels = result.get(area.getId());
				area.setAreaChannels(channels);
				Map<Integer, AreaChannel> channelMaps = new HashMap<Integer, AreaChannel>();
				for(AreaChannel channel : channels)
					channelMaps.put(channel.getChannelId(), channel);
				
				List<Map<Object, Object>> cables = new ArrayList<Map<Object, Object>>();
				// get channels' temperatures
				if(channelMaps.size() > 0){
					Map<Integer, List<String>> temps = temTaskService.getTemsByChannels(channelMaps.keySet());
					// filter channel temperatures
					if(temps != null && temps.size() > 0)
						for(Integer id : temps.keySet()){
							List<String> _ttemps = temps.get(id);
							AreaChannel ctmp = channelMaps.get(id);
							if(_ttemps != null && _ttemps.size() > 0)
								for(String _temps : _ttemps)
									if(StringUtils.isNotBlank(_temps)){
										String[] tmp = StringUtils.split(_temps, PinaoConstants.TEM_DATA_ELEMENT_SEP);
										
										if(ctmp != null){
											int start = ctmp.getScopeStart();
											int end = ctmp.getScopeEnd();
											if(start < tmp.length)
												for(int i = start; i < end && i < tmp.length; i ++){
													Map<Object, Object> _tt = new HashMap<Object, Object>();
													_tt.put("channel", id);
													_tt.put("length", i);
													_tt.put("temperature", tmp[i]);
													cables.add(_tt);
												}
										}
									}
						}
				}
				model.addAttribute("cablelist", cables);
			
				Map resultSet = cableService.findByArea(area,page,maxLinks);
				model.addAttribute("cablelist", resultSet.get("result"));
				model.addAttribute("totalPages",Math.ceil(Double.parseDouble(resultSet.get("totalPages").toString())/maxLinks));
				model.addAttribute("page",page);
			}*/
			model.addAttribute("area",area.getBackground());
		}
		model.addAttribute("requestPath",request.getRequestURL().toString());
		return "monitor/index";
	}
	
	@RequestMapping(value="/monitor/{area_id}.json" , method = RequestMethod.GET)
	public @ResponseBody Map<Object, List<Map<Object, Object>>>  findByAreaId_json(@PathVariable int area_id,Model model){
		Area area = areaService.findById(area_id);
		if(area != null){
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(area.getId());
			Map<Integer, List<AreaChannel>> result = channelService.findByAreaIds(ids);
			if(result != null){
				List<AreaChannel> channels = result.get(area.getId());
				area.setAreaChannels(channels);
				Map<Integer, AreaChannel> channelMaps = new HashMap<Integer, AreaChannel>();
				for(AreaChannel channel : channels)
					channelMaps.put(channel.getChannelId(), channel);
				
				Map<Object, List<Map<Object, Object>>> cables = new HashMap<Object, List<Map<Object, Object>>>();
				// get channels' temperatures
				if(channelMaps.size() > 0){
					Map<Integer, List<String>> temps = temTaskService.getTemsByChannels(channelMaps.keySet());
					// filter channel temperatures
					if(temps != null && temps.size() > 0)
						for(Integer id : temps.keySet()){
							List<String> _ttemps = temps.get(id);
							AreaChannel ctmp = channelMaps.get(id);
							List<Map<Object, Object>> _tmpcables = new ArrayList<Map<Object,Object>>();
							cables.put(id, _tmpcables);
							if(_ttemps != null && _ttemps.size() > 0)
								for(String _temps : _ttemps)
									if(StringUtils.isNotBlank(_temps)){
										String[] tmp = StringUtils.split(_temps, PinaoConstants.TEM_DATA_ELEMENT_SEP);
										
										if(ctmp != null){
											int start = ctmp.getScopeStart();
											int end = ctmp.getScopeEnd();
											if(start < tmp.length)
												for(int i = start; i < end && i < tmp.length; i ++){
													Map<Object, Object> _tt = new HashMap<Object, Object>();
													_tt.put("length", i);
													_tt.put("temperature", tmp[i]);
													_tmpcables.add(_tt);
												}
										}
									}
						}
				}
				return cables;
			}
		}
		return null;
	
	}

	
}
