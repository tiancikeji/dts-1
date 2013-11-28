package tianci.pinao.dts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.Cable;
import tianci.pinao.dts.services.CableService;

@Controller
public class MonitorController {
	
	@Autowired
	CableService cableService;

	@RequestMapping(value="/monitor" , method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("cablelist", cableService.findAll());
		return "monitor/index";
	}
	
	
	@RequestMapping(value="/monitor/{area_id}" , method = RequestMethod.GET)
	public String findByAreaId(@PathVariable int area_id,Model model){
		model.addAttribute("cablelist", cableService.findByArea(area_id));
		return "monitor/index";
	}
	
	@RequestMapping(value="/monitor/{area_id}.json" , method = RequestMethod.GET)
	public @ResponseBody List<Cable>  findByAreaId_json(@PathVariable int area_id,Model model){
		List<Cable> areaList = cableService.findByArea(area_id);
		return areaList;
	}
}
