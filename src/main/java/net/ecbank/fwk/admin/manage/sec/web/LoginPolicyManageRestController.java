package net.ecbank.fwk.admin.manage.sec.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.common.dto.Response;
import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.dto.LoginPolicyDto;
import net.ecbank.fwk.admin.manage.sec.dto.RoleInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.LoginPolicy;
import net.ecbank.fwk.admin.manage.sec.entity.RoleInfo;
import net.ecbank.fwk.admin.manage.sec.service.LoginPolicyManageService;
import net.ecbank.fwk.admin.manage.sec.service.RoleManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sec")
public class LoginPolicyManageRestController {
	
	@Autowired
	private LoginPolicyManageService loginPolicyMngService;
	
	@PostMapping("/searchUserLoignPolicyList")
	@ResponseBody
	public List<LoginPolicyDto> searchUserLoignPolicyList(@RequestBody LoginPolicyDto loginPolicyDto) {
		
		return loginPolicyMngService.searchUserLoginPolicyList(loginPolicyDto);
	}
	
	@PostMapping("/saveLoginPolicy")
	public LoginPolicyDto saveLoginPolicy(@RequestBody LoginPolicyDto loginPolicyDto) {
		
		System.out.println("save size : " + loginPolicyDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			loginPolicyMngService.saveLoginPolicy(loginPolicyDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		loginPolicyDto.setResponse(res);
		
		return loginPolicyDto;
	}
	
	@PostMapping("/deleteLoginPolicy")
	public LoginPolicyDto deleteLoginPolicy(@RequestBody LoginPolicyDto loginPolicyDto) {
		
		Response res = new Response();
		
		try {
			loginPolicyMngService.deleteLoginPolicy(loginPolicyDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		loginPolicyDto.setResponse(res);
		
		return loginPolicyDto;
	}
	
	
	
	
	private List<LoginPolicyDto> convertToDtoList(List<LoginPolicy> loginPolicyList) {
	    return loginPolicyList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private LoginPolicyDto convertToDto(LoginPolicy loginPolicy) {
		LoginPolicyDto loginPolicyDto = ModelMapperUtils.getModelMapper().map(loginPolicy, LoginPolicyDto.class);
		
	    return loginPolicyDto;
	}
}
