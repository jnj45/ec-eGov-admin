package net.ecbank.fwk.admin.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class AuthManageController {
	
	@RequestMapping("/authManage")
	public String authManage() {
		
		return "sys/authManage/authManage";
	}
}
