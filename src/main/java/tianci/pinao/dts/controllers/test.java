package tianci.pinao.dts.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller//生命一个跳转的类  action里的方法是的
public class test {

	@RequestMapping("test/login.do")
	public String  Creat(Locale locale, Model model){
		System.out.println("aaaaaa");
		return "index";
	}
	
	@RequestMapping("test/find.do")
	public String  find(Locale locale, Model model){
		System.out.println("bbbbbbb");
		return "find";
	}
	
	@RequestMapping("test/project.do")
	public String  project(Locale locale, Model model){
		System.out.println("cccccc");
		return "project";
	}
	@RequestMapping("test/params.do")
	public String  params(Locale locale, Model model){
		System.out.println("ddddddd");
		return "params";
	}
	

		@RequestMapping("test/help.do")
		public String  help(Locale locale, Model model){
			System.out.println("eeeeee");
			return "help";
		}
		
		@RequestMapping("test/body.do")
		public String  body(Locale locale, Model model){
			System.out.println("body");
			return "body";
		}
			
	
	
	
}
