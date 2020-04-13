package net.ecbank.fwk.admin.manage.log.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.manage.log.dto.LoginLogDto;
import net.ecbank.fwk.admin.manage.log.entity.LoginLog;
import net.ecbank.fwk.admin.manage.log.service.LoginLogService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/log")
public class LoginLogRestController {
	
	@Autowired
	private LoginLogService loginLogService;
	
	@PostMapping("/searchLoginLogList")
	@ResponseBody
	public Page<LoginLogDto> searchLoginLogList(@Valid LoginLogDto loginLogDto){
		
		Page<LoginLogDto> page = loginLogService.searchLoginLogList(loginLogDto);
		
		return page;
	}
	
	private List<LoginLogDto> convertToDtoList(List<LoginLog> loginLogList) {
	    return loginLogList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private LoginLogDto convertToDto(LoginLog loginLog) {
		LoginLogDto loginLogDto = ModelMapperUtils.getModelMapper().map(loginLog, LoginLogDto.class);
		
	    return loginLogDto;
	}
	
}
