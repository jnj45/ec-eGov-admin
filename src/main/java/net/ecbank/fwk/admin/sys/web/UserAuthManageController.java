package net.ecbank.fwk.admin.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class UserAuthManageController {
	
	@RequestMapping("/userAuthManage")
	public String authManage() {
		
		return "sys/userAuthManage/userAuthManage";
	}
}
