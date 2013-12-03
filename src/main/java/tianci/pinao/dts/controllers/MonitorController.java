package tianci.pinao.dts.controllers;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tianci.pinao.dts.Util.page;
import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.Cable;
import tianci.pinao.dts.services.AreaService;
import tianci.pinao.dts.services.CableService;

@Controller
public class MonitorController {
	
	@Autowired
	CableService cableService;
	
	@Autowired
	AreaService areaService;

	
	@RequestMapping(value="/monitor" , method = RequestMethod.GET)
	public String index(Model model){
		int pageIndex=1;
		return checkindex(model,pageIndex);
	}
	
	
	
	
	@RequestMapping(value="/checkmonitor/{pageIndex}" , method = RequestMethod.GET)
	public String checkindex(Model model,@PathVariable int pageIndex){
		
		List<Cable> list=cableService.find(pageIndex, 10);
		int counareaService=cableService.findcount();
		
		page p=new page();
		p.setDan(10);
		p.setAlltiao(counareaService);
		
		model.addAttribute("totalpage",p.settotalpagecountbyrs());
		
		model.addAttribute("count",counareaService);
		model.addAttribute("cablelist", list);
		model.addAttribute("pageIndex", pageIndex);
		return "monitor/index";
	}
	
	
	@RequestMapping(value="/monitor/{area_id}" , method = RequestMethod.GET)
	public String findByAreaId(@PathVariable int area_id,Model model){
		Area area = areaService.findById(area_id);
		model.addAttribute("cablelist", cableService.findByArea(area));
		model.addAttribute("area",area.getBackground());
		return "monitor/index";
	}
	
	@RequestMapping(value="/monitor/{area_id}.json" , method = RequestMethod.GET)
	public @ResponseBody List<Cable>  findByAreaId_json(@PathVariable int area_id,Model model){
		Area area = areaService.findById(area_id);
		List<Cable> areaList = cableService.findByArea(area);
		return areaList;
	}

	
}
