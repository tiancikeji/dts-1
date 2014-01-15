package tianci.pinao.dts.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.AreaChannel;
import tianci.pinao.dts.models.Channel;
import tianci.pinao.dts.models.Channels;
import tianci.pinao.dts.models.InnerChannel;
import tianci.pinao.dts.models.Sensor;
import tianci.pinao.dts.services.AreaService;
import tianci.pinao.dts.services.ChannelService;
import tianci.pinao.dts.services.ChannelsService;
import tianci.pinao.dts.services.SensorService;

@Controller
public class SettingsController {

	@Autowired
	AreaService areaService;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	SensorService sensorService;
	
	@Autowired
	private ChannelsService channelsService;

	@RequestMapping(value="/settings" , method = RequestMethod.GET)
	public String index(Model model){
		
		return "redirect:/settings/sensor";	}
	
	@RequestMapping(value="/settings/sensor" , method = RequestMethod.GET)
	public String sensor(Model model){
		
		return "settings/sensor";
	}
	

//增加管道
	@RequestMapping(value="/settings/addchannel" , method = RequestMethod.POST)
	public String addchanel(Model model,RedirectAttributes attributes,Channel channel){
		if(channelService.add(channel)){
			attributes.addFlashAttribute("status", "添加成功");
		}else{
			attributes.addFlashAttribute("status", "添加失败");			
		}
		return "redirect:/settings/communication";
	}
	
	//管道删除  
@RequestMapping(value="/settings/channel/delete/{id}",method=RequestMethod.GET)
public String deleteChannel(@PathVariable int id,RedirectAttributes attributes){
	if(channelService.delete(id)){
		attributes.addFlashAttribute("status", "删除成功");
		}else{
			attributes.addFlashAttribute("status", "删除失败");
		}
		return "redirect:/settings/communication";
}

	
	//增加设备
	@RequestMapping(value="/settings/addsensor" , method = RequestMethod.POST)
	public String addsensor(Model model,HttpServletRequest request,@ModelAttribute Sensor sensor,RedirectAttributes attributes){
		if(sensorService.add(sensor)){
			attributes.addFlashAttribute("status", "添加成功");
			}else{
				attributes.addFlashAttribute("status", "添加失败");
			}
		return "redirect:/settings/communication";
	}
	//删除  
	
	@RequestMapping(value="/settings/sensor/delete/{id}" ,method=RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes,Model model){
		if(sensorService.delete(id)){
			attributes.addFlashAttribute("status", "删除成功");
		}else{
			attributes.addFlashAttribute("status", "删除失败");
		}
		return "redirect:/settings/communication";
	}

	//全查询 发送管道和设备
	@RequestMapping(value="/settings/communication" , method = RequestMethod.GET)
	public String communication(Model model){
		List<Sensor> sensorList = sensorService.findall();
		model.addAttribute("sensorList", sensorList);
		model.addAttribute("channelList",channelService.findall());
		model.addAttribute("channels",channelsService.findChannels());
		return "settings/communication";
	}
	
	@RequestMapping(value="/settings/alert" , method = RequestMethod.GET)
	public String alert(Model model){
		
		return "settings/alert";
	}
	
	@RequestMapping(value="/settings/area" , method = RequestMethod.GET)
	public String area(Model model,HttpServletRequest request){
		List<Area> areaList = areaService.list();
		List<Integer> ids = new ArrayList<Integer>();
		if(areaList != null)
			for(Area area : areaList)
				ids.add(area.getId());
		Map<Integer, List<AreaChannel>> acs = channelService.findByAreaIds(ids);
		if(areaList != null)
			for(Area area : areaList)
				area.setAreaChannels(acs.get(area.getId()));
		model.addAttribute("areaList", areaList);
		model.addAttribute("channels",channelsService.findChannels().getChannels());
		return "settings/area";
	}
	

	
	
	@RequestMapping(value="/settings/area/delete/{id}" , method = RequestMethod.GET)
	public String area_delete(@PathVariable int id,RedirectAttributes attributes, Model model){
		if(areaService.delete(id)){
			attributes.addFlashAttribute("status", "删除成功");
		}else{
			attributes.addFlashAttribute("status", "删除失败");
		}
		
		return "redirect:/settings/area";
	}
	
	@RequestMapping(value="/settings/area.json" , method = RequestMethod.GET)
	public @ResponseBody List<Area> area_json(Model model){
		List<Area> areaList = areaService.list();
		
		return areaList;
	}

	
	@RequestMapping(value="/settings/area" , method = RequestMethod.POST)
	public String area_save(@ModelAttribute Area area,RedirectAttributes attributes,Model model,HttpServletRequest request){
		model.addAttribute("areaList", areaService.list());
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
	     //获取表单二进制name=file的那个图片
	        MultipartFile file = multipartRequest.getFile("file");   
	      //获取图片名字
	        String realFileName = file.getOriginalFilename();   
	   //创建文件夹
	        String ctxPath = request.getSession().getServletContext().getRealPath(   
	                "/")   
	                + "\\"+"assets\\" + "upload\\";   
	     
	        File dirPath = new File(ctxPath);   
	        if (!dirPath.exists()) {   
	            dirPath.mkdir();   
	        }   
	        File uploadFile = new File(ctxPath + realFileName);   
	        try {
				FileCopyUtils.copy(file.getBytes(), uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

	        String str="//assets//" + "upload//"+realFileName; 
	        
	        area.setBackground(str);
		
        if(area != null){
			List<AreaChannel> tmp = new ArrayList<AreaChannel>();
			if(area.getChannelids() != null && area.getChannelids().size() > 0){
				for(int i = 0; i < area.getChannelids().size(); i ++){
					Integer tmpC = area.getChannelids().get(i);
					if(tmpC != null && tmpC > 0){
						if(area.getScopestarts() != null && area.getScopestarts().size() > i){
							Integer tmpS = area.getScopestarts().get(i);
							if(tmpS != null && tmpS > 0){
								if(area.getScopeends() != null && area.getScopeends().size() > i){
									Integer tmpE = area.getScopeends().get(i);
									if(tmpE != null && tmpE > 0 && tmpE >= tmpS){
										AreaChannel ac = new AreaChannel();
										ac.setChannelId(tmpC);
										ac.setScopeStart(tmpS);
										ac.setScopeEnd(tmpE);
										tmp.add(ac);
									}
								}
							}
						}
					}
				}	
				area.setAreaChannels(tmp);
			}
        }
		
		if(areaService.add(area)){
			attributes.addFlashAttribute("status", "添加成功");
		}else{
			attributes.addFlashAttribute("status", "添加失败");
		}
		
		return "redirect:/settings/area";
	}
	
	@RequestMapping(value="/settings/system" , method = RequestMethod.GET)
	public String system(Model model){
		
		return "settings/system";
	}
	
	//设置光开关设置
	@RequestMapping(value="/settings/setchannels" , method = RequestMethod.POST)
	public String setChannels(Model model,HttpServletRequest request,@ModelAttribute Channels channels,RedirectAttributes attributes){
		if(channels != null){
			List<InnerChannel> tmp = new ArrayList<InnerChannel>();
			if(channels.getChannelids() != null && channels.getChannelids().size() > 0){
				for(int i = 0; i < channels.getChannelids().size(); i ++){
					Integer tmpC = channels.getChannelids().get(i);
					if(tmpC != null && tmpC > 0){
						if(channels.getChannellengths() != null && channels.getChannellengths().size() > i){
							Integer tmpL = channels.getChannellengths().get(i);
							if(tmpL != null && tmpL > 0){
								InnerChannel ic = new InnerChannel();
								ic.setChannel(tmpC);
								ic.setLength(tmpL);
								tmp.add(ic);
							}
						}
					}
				}
			}
			channels.setChannels(tmp);
			if(channelsService.saveChannels(channels)){
				attributes.addFlashAttribute("status", "添加成功");
			}else{
				attributes.addFlashAttribute("status", "添加失败");
			}
		}
		return "redirect:/settings/communication";
	}
}
