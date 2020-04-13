package net.ecbank.fwk.admin.manage.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class MenuManageController {
	
	@RequestMapping("/menuManage")
	public String menuManage() {
		
		return "sys/menuManage/menuManage";
	}
	
}
