package net.ecbank.fwk.admin.log.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LoginLogController {
	
	@RequestMapping("/loginLog")
	public String authManage() {
		
		return "log/loginLog/loginLog";
	}
	
}
