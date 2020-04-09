package net.ecbank.fwk.admin.sec.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.sec.dto.UserAuthDto;
import net.ecbank.fwk.admin.sec.service.UserAuthManageService;
import net.ecbank.fwk.admin.user.entity.Employee;
import net.ecbank.fwk.admin.user.entity.VendorUser;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sec")
public class UserAuthManageRestController {
	
	@Autowired
	private UserAuthManageService userAuthManageService;
	
	@PostMapping("/userList")
	@ResponseBody
	public List<UserAuthDto> authInfoList(@RequestBody UserAuthDto userAuthDto) {
		
		List list = userAuthManageService.searchUserList(userAuthDto);
		
		List<UserAuthDto> convertList = null;
		
		if(userAuthDto.getUserGbn().equals("EMP")) {
			convertList = convertToDtoListByEmp(list);
		} else {
			convertList = convertToDtoListByVndUsr(list);
		}
		
		return convertList;
	}
	
	private List<UserAuthDto> convertToDtoListByEmp(List<Employee> empList) {
	    return empList.stream().map(this::convertToDtoByEmp).collect(Collectors.toList());
	}
	
	private UserAuthDto convertToDtoByEmp(Employee employee) {
		UserAuthDto userAuthDto = ModelMapperUtils.getModelMapper().map(employee, UserAuthDto.class);
		
	    return userAuthDto;
	}
	
	private List<UserAuthDto> convertToDtoListByVndUsr(List<VendorUser> vndUsrList) {
	    return vndUsrList.stream().map(this::convertToDtoByVndUsr).collect(Collectors.toList());
	}
	
	private UserAuthDto convertToDtoByVndUsr(VendorUser vendorUser) {
		UserAuthDto userAuthDto = ModelMapperUtils.getModelMapper().map(vendorUser, UserAuthDto.class);
		
	    return userAuthDto;
	}
}