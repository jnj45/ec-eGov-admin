package net.ecbank.fwk.admin.log.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogExplorerController {
	
	@RequestMapping("/logExplorer")
	public String authManage() {
		
		return "log/logExplorer/logExplorer";
	}
	
}
