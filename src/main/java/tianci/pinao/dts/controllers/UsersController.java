package tianci.pinao.dts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UsersController {

	@RequestMapping(value="/users/login",method = RequestMethod.GET)
	public String login(Model model){
		
		return "users/login";
	}
	
	@RequestMapping(value="/users/logout",method = RequestMethod.GET)
	public String logout(Model model){
		
		return "users/logout";
	}
}
