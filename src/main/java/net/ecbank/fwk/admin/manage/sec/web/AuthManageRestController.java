package net.ecbank.fwk.admin.manage.sec.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.common.dto.Response;
import net.ecbank.fwk.admin.console.jmx.ServerConfigService;
import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.AuthInfo;
import net.ecbank.fwk.admin.manage.sec.service.AuthManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sec")
public class AuthManageRestController {
	
	@Autowired
	private AuthManageService authMngService;
	
	@Autowired
	private ServerConfigService serverConfigService;
	
	@PostMapping("/authInfoList")
	@ResponseBody
	public List<AuthInfoDto> authInfoList(@RequestBody AuthInfoDto authInfoDto) {
		
		List<AuthInfo> list = authMngService.searchAuthInfoList(authInfoDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveAuthInfo")
	public AuthInfoDto saveAuthInfo(@RequestBody AuthInfoDto authInfoDto) {
		
		System.out.println("save size : " + authInfoDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			authMngService.saveAuthInfo(authInfoDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		authInfoDto.setResponse(res);
		
		return authInfoDto;
	}
	
	@PostMapping("/deleteAuthInfo")
	public AuthInfoDto deleteAuthInfo(@RequestBody AuthInfoDto authInfoDto) {
		
		Response res = new Response();
		
		try {
			authMngService.deleteAuthInfo(authInfoDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		authInfoDto.setResponse(res);
		
		return authInfoDto;
	}
	
	@PostMapping("/reflectRuntime")
	public CommonDto reflectRuntime() {
		
		CommonDto commDto = new CommonDto();
		
		Response res = new Response();
		
		try {
			serverConfigService.reloadRolesAndUrlMapping();
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		commDto.setResponse(res);
		
		return commDto;
	}
	
	@PostMapping("/userAuthList")
	@ResponseBody
	public List<AuthInfoDto> userAuthList(@RequestBody AuthInfoDto authInfoDto) {
		
		List<AuthInfo> list = authMngService.searchUserAuthList(authInfoDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/deptAuthList")
	@ResponseBody
	public List<AuthInfoDto> deptAuthList(@RequestBody AuthInfoDto authInfoDto) {
		
		List<AuthInfo> list = authMngService.searchDeptAuthList(authInfoDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveUserAuthRelation")
	public AuthInfoDto saveUserAuthRelation(@RequestBody AuthInfoDto authInfoDto) {
		
		System.out.println("save size : " + authInfoDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			authMngService.saveUserAuthRelation(authInfoDto);
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		authInfoDto.setResponse(res);
		
		return authInfoDto;
	}
	
	@PostMapping("/saveDeptAuthRelation")
	public AuthInfoDto saveDeptAuthRelation(@RequestBody AuthInfoDto authInfoDto) {
		
		System.out.println("save size : " + authInfoDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			authMngService.saveDeptAuthRelation(authInfoDto);
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		authInfoDto.setResponse(res);
		
		return authInfoDto;
	}
	
	private List<AuthInfoDto> convertToDtoList(List<AuthInfo> authInfoList) {
	    return authInfoList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private AuthInfoDto convertToDto(AuthInfo authInfo) {
		AuthInfoDto authInfoDto = ModelMapperUtils.getModelMapper().map(authInfo, AuthInfoDto.class);
		authInfoDto.setModAuthCode(authInfo.getAuthCode());
		
		if(authInfo.getRegYn() == null || authInfo.getRegYn().equals("")) {
			authInfoDto.setRegYn("N");	
		} else {
			authInfoDto.setRegYn("Y");
		}
		
	    return authInfoDto;
	}
}
