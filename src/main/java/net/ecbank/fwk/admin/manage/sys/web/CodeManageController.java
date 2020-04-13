package net.ecbank.fwk.admin.manage.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class CodeManageController {
	
	@RequestMapping("/codeManage")
	public String codeManage() {
		
		return "sys/codeManage/codeManage";
	}
}
