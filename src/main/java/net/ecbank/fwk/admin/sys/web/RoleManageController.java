package net.ecbank.fwk.admin.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ecbank.fwk.admin.sys.service.CodeManageService;

@Controller
@RequestMapping("/sys")
public class RoleManageController {
	
	@RequestMapping("/roleManage")
	public String roleManage() {
		
		return "sys/roleManage/roleManage";
	}
}
