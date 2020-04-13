package net.ecbank.fwk.admin.manage.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class RoleManageController {
	
	@RequestMapping("/roleManage")
	public String roleManage() {
		
		return "sec/roleManage/roleManage";
	}
}
