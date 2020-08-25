package net.ecbank.fwk.admin.manage.log.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class MailLogController {
	
	@RequestMapping("/mailLog")
	public String authManage() {
		
		return "log/mailLog/mailLog";
	}
	
}
