package net.ecbank.fwk.admin.manage.sec.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.manage.sec.dto.LoginPolicyDto;
import net.ecbank.fwk.admin.manage.sec.entity.LoginPolicy;
import net.ecbank.fwk.admin.manage.sec.repository.LoginPolicyRepository;
import net.ecbank.fwk.admin.manage.sec.repository.LoginPolicyRepositoryImpl;

@Service
public class LoginPolicyManageService {
	
	@Autowired
	private LoginPolicyRepository loginPolicyRep;
	
	@Autowired
	private LoginPolicyRepositoryImpl loginPolicyRepImpl;
	
	public List<LoginPolicyDto> searchUserLoginPolicyList(LoginPolicyDto loginPolicyDto){
		
		List<LoginPolicyDto> list = null;
		
		if(loginPolicyDto.getUserGbn().equals("EMP")) {
			list = loginPolicyRepImpl.searchEmployeeLoginPolicyList(loginPolicyDto);
		}else {
			list = loginPolicyRepImpl.searchVendorLoginPolicyList(loginPolicyDto);
		}
		
		return list;
	}
	
	@Transactional
	public void saveLoginPolicy(List<LoginPolicyDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			LoginPolicyDto saveLoginPolicyDto = saveList.get(i);
			
			LoginPolicy loginPolicy = new LoginPolicy(saveLoginPolicyDto);
			
			loginPolicyRep.save(loginPolicy);
		}
		
	}
	
	@Transactional
	public void deleteLoginPolicy(List<LoginPolicyDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			LoginPolicyDto delLoginPolicyDto = deleteList.get(i);
			
			LoginPolicy loginPolicy = new LoginPolicy(delLoginPolicyDto.getUserId());
			
			loginPolicyRep.delete(loginPolicy);
		}
	}
}
