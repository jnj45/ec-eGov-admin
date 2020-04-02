package net.ecbank.fwk.admin.sys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.sys.dto.AuthInfoDto;
import net.ecbank.fwk.admin.sys.dto.CodeGroupDto;
import net.ecbank.fwk.admin.sys.dto.Response;
import net.ecbank.fwk.admin.sys.dto.RoleInfoDto;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;
import net.ecbank.fwk.admin.sys.service.RoleManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sys")
public class RoleManageRestController {
	
	@Autowired
	private RoleManageService roleMngService;
	
	@PostMapping("/roleInfoList")
	@ResponseBody
	public List<RoleInfoDto> roleInfoList(@RequestBody RoleInfoDto roleInfoDto) {
		
		List<RoleInfo> list = roleMngService.searchRoleInfoList(roleInfoDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveRoleInfo")
	public RoleInfoDto saveRoleInfo(@RequestBody RoleInfoDto roleInfoDto) {
		
		System.out.println("save size : " + roleInfoDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			roleMngService.saveRoleInfo(roleInfoDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		roleInfoDto.setResponse(res);
		
		return roleInfoDto;
	}
	
	@PostMapping("/deleteRoleInfo")
	public RoleInfoDto deleteRoleInfo(@RequestBody RoleInfoDto roleInfoDto) {
		
		Response res = new Response();
		
		try {
			roleMngService.deleteRoleInfo(roleInfoDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		roleInfoDto.setResponse(res);
		
		return roleInfoDto;
	}
	
	@PostMapping("/authRoleList")
	@ResponseBody
	public List<RoleInfoDto> authRoleList(@RequestBody AuthInfoDto authInfoDto) {
		
		List<RoleInfo> list = roleMngService.searchAuthRoleList(authInfoDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveAuthRelation")
	public RoleInfoDto saveAuthRelation(@RequestBody RoleInfoDto roleInfoDto) {
		
		System.out.println("save size : " + roleInfoDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			roleMngService.saveAuthRelation(roleInfoDto.getSaveList(),roleInfoDto.getAuthCode());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		roleInfoDto.setResponse(res);
		
		return roleInfoDto;
	}
	
	
	private List<RoleInfoDto> convertToDtoList(List<RoleInfo> roleInfoList) {
	    return roleInfoList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private RoleInfoDto convertToDto(RoleInfo roleInfo) {
		RoleInfoDto roleInfoDto = ModelMapperUtils.getModelMapper().map(roleInfo, RoleInfoDto.class);
		roleInfoDto.setModRoleCode(roleInfo.getRoleCode());
		
		if(roleInfo.getRoleRegYn() == null || roleInfo.getRoleRegYn().equals("")) {
			roleInfoDto.setRoleRegYn("N");
		}else {
			roleInfoDto.setRoleRegYn("Y");
		}
		
	    return roleInfoDto;
	}
}
