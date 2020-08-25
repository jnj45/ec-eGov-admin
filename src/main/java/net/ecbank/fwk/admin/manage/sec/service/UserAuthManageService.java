package net.ecbank.fwk.admin.manage.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.manage.sec.dto.UserAuthDto;
import net.ecbank.fwk.admin.manage.user.repository.EcUserRepository;

@Service
public class UserAuthManageService {
	
	@Autowired
	private EcUserRepository ecUserRep;
	
	public Page<UserAuthDto> searchUserList(UserAuthDto userAuthDto){
		
		System.out.println(userAuthDto);
		
		System.out.println(userAuthDto.getPage());
		
		Page<UserAuthDto> list = null;
		
		PageRequest pr = PageRequest.of(userAuthDto.getPage()-1,userAuthDto.getRows(),Sort.by(Direction.DESC,"userCls"));
		
		if(userAuthDto.getUserGbn().equals("EMP")) {
			//list = empRep.findByEmpNoContainingAndEmpNmContaining(userAuthDto.getUserId(), userAuthDto.getUserNm());
			list = ecUserRep.findByUserIdContainingAndUserNmContainingAndUserClsAndCoCd(userAuthDto.getUserId(), userAuthDto.getUserNm(), "B",userAuthDto.getCoCd(), pr);
		}else {
			list = ecUserRep.findByUserIdContainingAndUserNmContainingAndUserClsAndCoCd(userAuthDto.getUserId(), userAuthDto.getUserNm(), "S",userAuthDto.getCoCd(), pr);
			//list = venUsrRep.findByUserIdContainingAndUserNmContaining(userAuthDto.getUserId(), userAuthDto.getUserNm());
		}
		
		return list;
	}
	
}
