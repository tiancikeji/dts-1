package tianci.pinao.dts.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.Cable;
import tianci.pinao.dts.services.AreaService;
import tianci.pinao.dts.services.CableService;

@Controller
public class MonitorController {
	
	@Autowired
	CableService cableService;
	
	@Autowired
	AreaService areaService;

	
//	@RequestMapping(value="/monitor" , method = RequestMethod.GET)
//	public String index(Model model){
//		int pageIndex=1;
//		return checkindex(model,pageIndex);
//	}
	
	
	@RequestMapping(value="/monitor/{area_id}" , method = RequestMethod.GET)
	public String findByAreaId(HttpServletRequest request,@PathVariable int area_id,Model model){
		int page = 1;
		int maxLinks = 10;
		if(null != request.getParameter("page")){
			page = Integer.parseInt(request.getParameter("page"));
		}
		Area area = areaService.findById(area_id);
		Map resultSet = cableService.findByArea(area,page,maxLinks);
		model.addAttribute("cablelist", resultSet.get("result"));
		model.addAttribute("totalPages",Math.ceil(Double.parseDouble(resultSet.get("totalPages").toString())/maxLinks));
		model.addAttribute("page",page);
		model.addAttribute("area",area.getBackground());
		model.addAttribute("requestPath",request.getRequestURL().toString());
		return "monitor/index";
	}
	
	@RequestMapping(value="/monitor/{area_id}.json" , method = RequestMethod.GET)
	public @ResponseBody List<Cable>  findByAreaId_json(@PathVariable int area_id,Model model){
		int page = 0;
		int maxLinks = 10;
		Area area = areaService.findById(area_id);
		List<Cable> areaList = (List<Cable>) cableService.findByArea(area,page,maxLinks).get("result");
		return areaList;
	}

	
}
