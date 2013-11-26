package tianci.pinao.dts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.services.AreaService;
import tianci.pinao.dts.services.CableService;


@Controller
public class SettingsController {

	@Autowired
	AreaService areaService;
	
	@RequestMapping(value="/settings" , method = RequestMethod.GET)
	public String index(Model model){
		
		return "redirect:/settings/sensor";
	}
	
	@RequestMapping(value="/settings/sensor" , method = RequestMethod.GET)
	public String sensor(Model model){
		
		return "settings/sensor";
	}
	
	@RequestMapping(value="/settings/communication" , method = RequestMethod.GET)
	public String communication(Model model){
		
		return "settings/communication";
	}
	
	@RequestMapping(value="/settings/alert" , method = RequestMethod.GET)
	public String alert(Model model){
		
		return "settings/alert";
	}
	
	@RequestMapping(value="/settings/area" , method = RequestMethod.GET)
	public String area(Model model){
		List<Area> areaList = areaService.list();
		model.addAttribute("areaList", areaList);
		return "settings/area";
	}
	
	
	@RequestMapping(value="/settings/area.json" , method = RequestMethod.GET)
	public @ResponseBody List<Area> area_json(Model model){
		List<Area> areaList = areaService.list();
		return areaList;
	}
	
	@RequestMapping(value="/settings/area" , method = RequestMethod.POST)
	public String area_save(@ModelAttribute Area area,RedirectAttributes attributes,Model model){
		model.addAttribute("areaList", areaService.list());
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
}
