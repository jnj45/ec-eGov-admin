package net.ecbank.fwk.admin.manage.sec.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.manage.sec.dto.UserAuthDto;
import net.ecbank.fwk.admin.manage.sec.service.UserAuthManageService;
import net.ecbank.fwk.admin.manage.user.entity.EcUser;
import net.ecbank.fwk.admin.manage.user.entity.Employee;
import net.ecbank.fwk.admin.manage.user.entity.VendorUser;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sec")
public class UserAuthManageRestController {
	
	@Autowired
	private UserAuthManageService userAuthManageService;
	
	@PostMapping("/userList")
	@ResponseBody
	public Page<UserAuthDto> authInfoList(@RequestBody UserAuthDto userAuthDto) {
		
		Page<UserAuthDto> list = userAuthManageService.searchUserList(userAuthDto);
		
		/*List<UserAuthDto> convertList = null;*/
		/*
		if(userAuthDto.getUserGbn().equals("EMP")) {
			convertList = convertToDtoListByEcUser(list);
		} else {
			convertList = convertToDtoListByEcUser(list);
		}*/
		
		//System.out.println("조회 건수 : " + convertList.size());
		
		return list;
	}
	
	private List<UserAuthDto> convertToDtoListByEcUser(List<EcUser> ecUserList) {
	    return ecUserList.stream().map(this::convertToDtoByEcUser).collect(Collectors.toList());
	}
	
	private UserAuthDto convertToDtoByEcUser(EcUser ecUser) {
		UserAuthDto userAuthDto = ModelMapperUtils.getModelMapper().map(ecUser, UserAuthDto.class);
		
	    return userAuthDto;
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
