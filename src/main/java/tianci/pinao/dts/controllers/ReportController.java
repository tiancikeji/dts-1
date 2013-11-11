package tianci.pinao.dts.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "report/index";
	}
}
