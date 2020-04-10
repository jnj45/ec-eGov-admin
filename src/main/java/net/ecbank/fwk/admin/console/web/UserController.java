package net.ecbank.fwk.admin.console.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class UserController {
	
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		
		return "loginForm";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/loginSuc")
	public String loginSuc() {
		
		return "loginSuc";
	}
	
	@RequestMapping(value="/userDenied")
	public String userDenied() {
		
		return "userDenied";
	}
	
	@RequestMapping(value="/logoutSuc")
	public String logoutSuc() {
		
		return "logoutSuc";
	}
	
	@RequestMapping(value="/logout")
	public void logout() {
		
	}
	
}
