package net.ecbank.fwk.admin.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class UserAuthManageController {
	
	@RequestMapping("/userAuthManage")
	public String authManage() {
		
		return "sec/userAuthManage/userAuthManage";
	}
}
