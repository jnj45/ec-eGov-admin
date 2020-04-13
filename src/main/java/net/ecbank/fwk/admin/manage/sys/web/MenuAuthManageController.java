package net.ecbank.fwk.admin.manage.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ecbank.fwk.admin.manage.sec.service.AuthManageService;

@Controller
@RequestMapping("/sys")
public class MenuAuthManageController {
	
	@Autowired
	private AuthManageService authManageService;
	
	@RequestMapping("/menuAuthManage")
	public String menuManage(ModelMap model) {
		
		model.put("authList", authManageService.searchAuthInfoAllList());
		
		return "sys/menuAuthManage/menuAuthManage";
	}
	
}
