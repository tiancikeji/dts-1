package tianci.pinao.dts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProjectController {

	@RequestMapping(value="/project",method = RequestMethod.GET)
	public String index(Model model){
		
		return "project/index";
	}
	
	@RequestMapping(value="/project/info",method = RequestMethod.GET)
	public String info(Model model){
		
		return "project/info";
	}
	
	@RequestMapping(value="/project/graph",method = RequestMethod.GET)
	public String graph(Model model){
		
		return "project/graph";
	}
}
