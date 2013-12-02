package tianci.pinao.dts.controllers;

import java.io.File;  
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.services.AreaService;



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
	public String area(Model model,HttpServletRequest request){
		List<Area> areaList = areaService.list();
		model.addAttribute("areaList", areaList);
		return "settings/area";
	}
	
	
	@RequestMapping(value="/settings/area/delete/{id}" , method = RequestMethod.GET)
	public String area_delete(@PathVariable int id,RedirectAttributes attributes, Model model){
		if(areaService.delete(id)){
			attributes.addFlashAttribute("status", "删除成功");
		}else{
			attributes.addFlashAttribute("status", "删除失败");
		}
		
		return "redirect:/settings/area";
	}
	
	@RequestMapping(value="/settings/area.json" , method = RequestMethod.GET)
	public @ResponseBody List<Area> area_json(Model model){
		List<Area> areaList = areaService.list();
		return areaList;
	}
	
	@RequestMapping(value="/settings/area" , method = RequestMethod.POST)
	public String area_save(@ModelAttribute Area area,RedirectAttributes attributes,Model model,HttpServletRequest request){
		model.addAttribute("areaList", areaService.list());
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
	     
	        MultipartFile file = multipartRequest.getFile("file");   
	      
	        String realFileName = file.getOriginalFilename();   
	   
	        String ctxPath = request.getSession().getServletContext().getRealPath(   
	                "/")   
	                + "\\"+"assets\\" + "upload\\";   
	     
	        File dirPath = new File(ctxPath);   
	        if (!dirPath.exists()) {   
	            dirPath.mkdir();   
	        }   
	        File uploadFile = new File(ctxPath + realFileName);   
	        try {
				FileCopyUtils.copy(file.getBytes(), uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		area.setBackground(realFileName);
		
		
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
