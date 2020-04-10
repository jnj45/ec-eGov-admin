package net.ecbank.fwk.admin.log.entity;

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
@Table(name="COMTNLOGINLOG")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class LoginLog {
	
	@Id
	@Column(name="LOG_ID", columnDefinition ="char")
	private String logId;
	
	@Column(name="CONECT_ID")
	private String conectId; 
	
	@Column(name="CONECT_IP")
	private String conectIp;
	
	@Column(name="CONECT_MTHD", columnDefinition ="char")
	private String conectMthd;
	
	@Column(name="ERROR_OCCRRNC_AT", columnDefinition ="char")
	private String errorOccrrncAt;
	
	@Column(name="ERROR_CODE", columnDefinition ="char")
	private String errorCode;
	
	@Column(name="CREAT_DT")
	private LocalDateTime createDate;
}
