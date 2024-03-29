package net.ecbank.fwk.admin.manage.log.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.log.dto.LoginLogDto;
import net.ecbank.fwk.admin.manage.log.entity.LoginLog;

public interface LoginLogRepository  extends JpaRepository<LoginLog, String>{
	
	Page<LoginLogDto> findByConectIdContainingAndConectIpContainingAndConectMthdAndErrorOccrrncAt(String conectId,String conectIp,String conectMthd,String errorOccrrncAt, Pageable pr); 
	
}
