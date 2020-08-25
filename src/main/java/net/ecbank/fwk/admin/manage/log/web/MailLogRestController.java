package net.ecbank.fwk.admin.manage.log.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.manage.log.dto.MailLogDto;
import net.ecbank.fwk.admin.manage.log.entity.MailLog;
import net.ecbank.fwk.admin.manage.log.service.MailLogService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/log")
public class MailLogRestController {
	
	@Autowired
	private MailLogService mailLogService;
	
	@PostMapping("/searchMailLogList")
	@ResponseBody
	public Page<MailLogDto> searchMailLogList(@RequestBody MailLogDto mailLogDto){
		
		Page<MailLogDto> page = mailLogService.searchMailLogList(mailLogDto);
		
		return page;
	}
	
	@PostMapping("/searchMailLogInfo")
	public MailLogDto searchMailLogInfo(@RequestBody MailLogDto mailLogDto){
		
		MailLog mailLog = mailLogService.searchMailLogInfo(mailLogDto);
		
		return convertToDto(mailLog);
	}
	
	private MailLogDto convertToDto(MailLog mailLog) {
		MailLogDto mailLogDto = ModelMapperUtils.getModelMapper().map(mailLog, MailLogDto.class);
		
	    return mailLogDto;
	}
}
