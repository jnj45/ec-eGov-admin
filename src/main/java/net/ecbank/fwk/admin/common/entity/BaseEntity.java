package net.ecbank.fwk.admin.common.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {
	
	@CreatedBy
	@Column(updatable = false, name="FRST_REGISTER_ID")
	private String createdBy;
	
	@LastModifiedBy
	@Column(name="LAST_UPDUSR_ID")
	private String lastModifiedBy;
}
