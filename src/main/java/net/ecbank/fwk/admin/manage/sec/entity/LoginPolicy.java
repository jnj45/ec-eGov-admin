package net.ecbank.fwk.admin.manage.sec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ecbank.fwk.admin.common.entity.BaseEntity;
import net.ecbank.fwk.admin.manage.sec.dto.LoginPolicyDto;

@Getter @Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="COMTNLOGINPOLICY")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class LoginPolicy extends BaseEntity {
	
	@Id
	@Column(name="EMPLYR_ID")
	private String userId;
	
	@Column(name="IP_INFO")
	private String ipInfo;
	
	@Column(name="DPLCT_PERM_AT", columnDefinition ="char")
	private String duplicatePermmitYn;
	
	@Column(name="LMTT_AT", columnDefinition ="char")
	private String limitYn;
	
	public LoginPolicy(LoginPolicyDto dto) {
		this.userId = dto.getUserId();
		this.ipInfo = dto.getIpInfo();
		this.duplicatePermmitYn = dto.getDuplicatePermmitYn();
		this.limitYn = dto.getLimitYn();
	}
	
	public LoginPolicy(String userId) {
		this.userId = userId;
	}
}
