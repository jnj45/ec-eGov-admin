package net.ecbank.fwk.admin.manage.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.manage.log.dto.MailLogDto;
import net.ecbank.fwk.admin.manage.log.entity.MailLog;
import net.ecbank.fwk.admin.manage.log.repository.MailLogRepository;
import net.ecbank.fwk.admin.manage.log.repository.MailLogRepositoryImpl;

@Service
public class MailLogService {
	
	
	@Autowired
	private MailLogRepositoryImpl mailLogRepImpl; 
	
	@Autowired
	private MailLogRepository mailLogRep; 
	
	public Page<MailLogDto> searchMailLogList(MailLogDto mailLogDto){
		
		PageRequest pr = PageRequest.of(mailLogDto.getPage()-1,mailLogDto.getRows(),Sort.by(Direction.DESC,"crtDt"));
		
		return mailLogRepImpl.searchMailLogList(mailLogDto, pr);
	}
	
	public MailLog searchMailLogInfo(MailLogDto mailLogDto){
		
		return mailLogRep.findBySeq(mailLogDto.getSeq());
	}
	
}
