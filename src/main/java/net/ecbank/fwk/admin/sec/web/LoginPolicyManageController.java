package net.ecbank.fwk.admin.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class LoginPolicyManageController {
	
	@RequestMapping("/loginPolicyManage")
	public String loginPolicyManage() {
		
		return "sec/loginPolicyManage/loginPolicyManage";
	}
}
