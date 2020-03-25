package net.ecbank.fwk.admin.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;

//@EntityListeners(AuditingEntityListener.class) //META-INF/orm.xml에 정의
@MappedSuperclass
@Getter
public class BaseTimeEntity {
	
	@CreatedDate
	@Column(updatable = false, name="FRST_REGIST_PNTTM")
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name="LAST_UPDT_PNTTM")
	private LocalDateTime lastModifiedDate;
	
}
