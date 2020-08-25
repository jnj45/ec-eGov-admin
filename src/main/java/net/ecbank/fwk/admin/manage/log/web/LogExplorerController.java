package net.ecbank.fwk.admin.manage.log.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogExplorerController {
	
	@Autowired
	private Environment environment;
	
	@RequestMapping("/logExplorer")
	public String authManage(ModelMap model) {
		
		String intanceList =  environment.getProperty("ecbank.fwk.admin.log.ins.id");
		
		model.put("instanceList", intanceList.split(","));
		
		return "log/logExplorer/logExplorer";
	}
	
}
