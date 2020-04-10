package net.ecbank.fwk.admin.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.log.dto.LoginLogDto;
import net.ecbank.fwk.admin.log.repository.LoginLogRepository;
import net.ecbank.fwk.admin.log.repository.LoginLogRepositoryImpl;

@Service
public class LoginLogService {
	
	@Autowired
	private LoginLogRepository loginLogRep;
	
	@Autowired
	private LoginLogRepositoryImpl loginLogRepImpl;
	
	public Page<LoginLogDto> searchLoginLogList(LoginLogDto loginLogDto){
		
		PageRequest pr = PageRequest.of(loginLogDto.getPage()-1,loginLogDto.getRows(),Sort.by(Direction.DESC,"createDate"));
		
		return loginLogRepImpl.searchLoginLogList(loginLogDto, pr);
	}
	
}
