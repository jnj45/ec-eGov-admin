package net.ecbank.fwk.admin.manage.log.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.manage.log.entity.LoginLog;

@Data
public class LoginLogDto extends CommonDto {
	
	private String logId;
	private String conectId; 
	private String conectIp;
	private String conectMthd;
	private String errorOccrrncAt;
	private String errorCode;
	private LocalDateTime createDate;
	private String coCode;
	private String accessDateBgn;
	private String accessDateEnd;
	private String formatterCreateDate;
	
	private List<LoginLogDto> searchList;
	
	public LoginLogDto() {
		
	}
	
	@QueryProjection
	public LoginLogDto(LoginLog loginLog) {
		
		this.logId = loginLog.getLogId();
		this.conectId = loginLog.getConectId();
		this.conectIp = loginLog.getConectIp();
		this.conectMthd = loginLog.getConectMthd();
		this.errorOccrrncAt = loginLog.getErrorOccrrncAt();
		this.errorCode = loginLog.getErrorCode();
		this.createDate = loginLog.getCreateDate();
		this.coCode = loginLog.getCoCode();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
		this.formatterCreateDate = loginLog.getCreateDate().format(formatter);
		
	}
	
}
