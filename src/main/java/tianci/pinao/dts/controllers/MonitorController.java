package tianci.pinao.dts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
