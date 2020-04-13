package net.ecbank.fwk.admin.manage.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class AuthManageController {
	
	@RequestMapping("/authManage")
	public String authManage() {
		
		return "sec/authManage/authManage";
	}
}
