package net.ecbank.fwk.admin.sec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sec.dto.UserAuthDto;
import net.ecbank.fwk.admin.user.repository.EmployeeRepository;
import net.ecbank.fwk.admin.user.repository.VendorUserRepository;

@Service
public class UserAuthManageService {
	
	@Autowired
	private EmployeeRepository empRep;
	
	@Autowired
	private VendorUserRepository venUsrRep;
	
	public List searchUserList(UserAuthDto userAuthDto){
		
		List list = null;
		
		if(userAuthDto.getUserGbn().equals("EMP")) {
			list = empRep.findByEmpNoContainingAndEmpNmContaining(userAuthDto.getUserId(), userAuthDto.getUserNm());
		}else {
			list = venUsrRep.findByUserIdContainingAndUserNmContaining(userAuthDto.getUserId(), userAuthDto.getUserNm());
		}
		
		return list;
	}
	
}
