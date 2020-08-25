package net.ecbank.fwk.admin.manage.log.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.manage.log.entity.MailLog;

@Data
public class MailLogDto extends CommonDto {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
	
	private String seq;
	private String title; 
	private String recName;
	private String recEmail;
	private String contents;
	private LocalDateTime regDate;
	private String fomatterRegDate;
	
	private LocalDateTime sendDate;
	private String fomatterSendDate;
	
	private int tryCount;
	private String errorMessage;
	private String senderName;
	private String senderEmail;
	private String sendYn;
	private String mailId;
	private String refNo1;
	private String refNo2;
	private String crtId;
	private String modId;
	private LocalDateTime crtDt;
	private String fomatterCrtDt;
	private LocalDateTime modDt;
	private String fomatterModDt;
	private String coCode;

	private List<MailLogDto> searchList;
	
	public MailLogDto() {
		
	}
	
	public String getFomatterRegDate() {
		
		if(this.regDate != null) {
			return this.regDate.format(formatter);
		}else {
			return "";
		}
		
	}
	
	public String getFomatterSendDate() {
		
		if(this.sendDate != null) {
			return this.sendDate.format(formatter);
		}else {
			return "";
		}
		
	}
	
	public String getFomatterCrtDt() {
		
		if(this.crtDt != null) {
			return this.crtDt.format(formatter);
		}else {
			return "";
		}
		
	}
	
	@QueryProjection
	public MailLogDto(MailLog mailLog) {
		
		this.seq = mailLog.getSeq();
		this.title = mailLog.getTitle(); 
		this.recName = mailLog.getRecName();
		this.recEmail = mailLog.getRecEmail();
		this.contents = mailLog.getContents();
		this.regDate = mailLog.getRegDate();
		this.sendDate = mailLog.getSendDate();
		this.tryCount = mailLog.getTryCount();
		this.errorMessage = mailLog.getErrorMessage();
		this.senderName = mailLog.getSenderName();
		this.senderEmail = mailLog.getSenderEmail();
		this.sendYn = mailLog.getSendYn();
		this.mailId = mailLog.getMailId();
		this.refNo1 = mailLog.getRefNo1();
		this.refNo2 = mailLog.getRefNo2();
		this.crtId = mailLog.getCrtId();
		this.modId = mailLog.getModId();
		this.crtDt = mailLog.getCrtDt();
		this.modDt = mailLog.getModDt();
		this.coCode = mailLog.getCoCode();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
		if(mailLog.getRegDate() != null) {
			this.fomatterRegDate = mailLog.getRegDate().format(formatter);
		}
		
		if(mailLog.getSendDate() != null) {
			this.fomatterSendDate = mailLog.getSendDate().format(formatter);
		}
		
		this.fomatterCrtDt = mailLog.getCrtDt().format(formatter);
		
	}
	
}
