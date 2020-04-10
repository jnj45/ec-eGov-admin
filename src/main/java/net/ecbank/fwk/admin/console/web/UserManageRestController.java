package net.ecbank.fwk.admin.console.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.cmm.dto.Response;
import net.ecbank.fwk.admin.console.dto.UserDto;
import net.ecbank.fwk.admin.console.entity.User;
import net.ecbank.fwk.admin.console.service.UserManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/console")
public class UserManageRestController {
	
	@Autowired
	private UserManageService userManageService;
	
	@PostMapping("/userList")
	public List<UserDto> codeGrpList(@RequestBody UserDto userDto) {
		
		List<User> list = userManageService.searchUserList(userDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveUser")
	public UserDto saveUser(@RequestBody UserDto userDto) {
		
		System.out.println("save size : " + userDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			userManageService.saveUser(userDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		userDto.setResponse(res);
		
		return userDto;
	}
	
	@PostMapping("/deleteUser")
	public UserDto deleteUser(@RequestBody UserDto userDto) {
		
		Response res = new Response();
		
		try {
			userManageService.deleteUser(userDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		userDto.setResponse(res);
		
		return userDto;
	}
	
	private List<UserDto> convertToDtoList(List<User> userList) {
	    return userList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private UserDto convertToDto(User user) {
		UserDto userDto = ModelMapperUtils.getModelMapper().map(user, UserDto.class);
		
		userDto.setModUserId(user.getUserId());
		userDto.setPassword("-");
		
	    return userDto;
	}
	
}
