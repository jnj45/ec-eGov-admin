package net.ecbank.fwk.admin.manage.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class ProgramManageController {
	
	@RequestMapping("/programManage")
	public String programManage() {
		
		return "sys/programManage/programManage";
	}
	
}
