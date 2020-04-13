package net.ecbank.fwk.admin.manage.sec.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="COMTNAUTHORROLERELATE")
@IdClass(AuthRoleRelKey.class)
//@ToString(exclude = {"authInfo"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class AuthRoleRel {
	
	/*@Id
	@Column(name="AUTHOR_CODE")
	private String authCode;
	
	@Id
	@JsonIgnore
	@Column(name="ROLE_CODE")
	private String roleCode;*/
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_CODE")
	private AuthInfo authInfo;
	
	@Id
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROLE_CODE")
	private RoleInfo roleInfo;
	
	@CreatedDate
	@Column(updatable = false, name="CREAT_DT")
	private LocalDateTime createdDate;
	
	public AuthRoleRel(String authCode, String roleCode) {
		
		/*this.authCode = authCode;
		this.roleCode = roleCode;*/
		
		this.authInfo = new AuthInfo(authCode);
		this.roleInfo = new RoleInfo(roleCode);
	}
}
