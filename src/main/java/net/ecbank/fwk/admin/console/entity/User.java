package net.ecbank.fwk.admin.console.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.ecbank.fwk.admin.console.dto.UserDto;
import net.ecbank.fwk.admin.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EA_USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User extends BaseEntity{
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NM")
	private String userNm;
	
	@Column(name="DEPT_NM")
	private String deptNm;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="PWD_FAIL_CNT")
	private int pwdFailCnt;
	
	@Column(name="LAST_PWD_CHG_DT")
	private LocalDateTime lastPwdChgDt;
	
	@Column(name="LAST_LOGIN_DT")
	private LocalDateTime lastLoginDt;
	
	@Column(name="USE_YN")
	private String useYn;
	
	public User(String userId) {
		this.userId = userId;
	}
	
	public User(UserDto dto) {
		
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(10);
		
		this.userId = dto.getModUserId();
		this.userNm = dto.getUserNm();
		this.deptNm = dto.getDeptNm();
		this.mobile = dto.getMobile();
		this.email = dto.getEmail();
		this.tel = dto.getTel();
		this.password = bcryptPasswordEncoder.encode(dto.getPassword());
		this.pwdFailCnt = dto.getPwdFailCnt();
		this.lastPwdChgDt = dto.getLastPwdChgDt();
		this.lastLoginDt = dto.getLastLoginDt();
		this.useYn = dto.getUseYn();
		
	}
	
}
