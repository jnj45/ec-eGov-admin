package net.ecbank.fwk.admin.manage.log.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EC_MAIL_SEND")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class MailLog {
	
	@Id
	@Column(name="SEQ", columnDefinition ="bigint")
	private String seq;
	
	@Column(name="TITLE")
	private String title; 
	
	@Column(name="REC_NAME", columnDefinition ="nvarchar")
	private String recName;
	
	@Column(name="REC_EMAIL")
	private String recEmail;
	
	@Column(name="CONTENTS")
	private String contents;
	
	@Column(name="REG_DATE")
	private LocalDateTime regDate;
	
	@Column(name="SEND_DATE")
	private LocalDateTime sendDate;
	
	@Column(name="TRY_COUNT")
	private int tryCount;
	
	@Column(name="ERROR_MESSAGE", columnDefinition ="nvarchar")
	private String errorMessage;
	
	@Column(name="SENDER_NAME", columnDefinition ="nvarchar")
	private String senderName;
	
	@Column(name="SENDER_EMAIL")
	private String senderEmail;
	
	@Column(name="SEND_YN",columnDefinition="char")
	private String sendYn;
	
	@Column(name="MAIL_ID")
	private String mailId;
	
	@Column(name="REF_NO1")
	private String refNo1;
	
	@Column(name="REF_NO2")
	private String refNo2;
	
	@Column(name="CRT_ID")
	private String crtId;
	
	@Column(name="MOD_ID")
	private String modId;
	
	@Column(name="CRT_DT")
	private LocalDateTime crtDt;
	
	@Column(name="MOD_DT")
	private LocalDateTime modDt;
	
	@Column(name="CO_CD",columnDefinition="char" )
	private String coCode;
	
}
