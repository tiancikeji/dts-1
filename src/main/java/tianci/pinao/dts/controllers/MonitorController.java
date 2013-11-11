package tianci.pinao.dts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MonitorController {

	@RequestMapping(value="/monitor" , method = RequestMethod.GET)
	public String index(Model model){
		
		return "monitor/index";
	}
}
