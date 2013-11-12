package tianci.pinao.dts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SettingsController {

	
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
	
	@RequestMapping(value="/settings/system" , method = RequestMethod.GET)
	public String system(Model model){
		
		return "settings/system";
	}
}
