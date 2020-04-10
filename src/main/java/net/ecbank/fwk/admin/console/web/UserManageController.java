package net.ecbank.fwk.admin.console.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/console")
public class UserManageController {
	
	@RequestMapping("/userManage")
	public String authManage() {
		
		return "console/userManage/userManage";
	}
	
}
