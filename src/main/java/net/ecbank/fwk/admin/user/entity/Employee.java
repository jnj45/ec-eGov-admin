package net.ecbank.fwk.admin.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.ecbank.fwk.admin.entity.BaseEntity;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="EF_EMP")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class Employee extends BaseEntity {
	
	@Id
	@Column(name="EMP_NO")
	private String empNo;
	
	@Column(name="EMP_NM")
	private String empNm;
	
	@Column(name="DEPT_CD")
	private String deptCd;
	
	@Column(name="DEPT_NM")
	private String deptNm;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="FAX")
	private String fax;
	
	@Column(name="JOB")
	private String job;
	
	@Column(name="DUTY")
	private String duty;
	
	@Column(name="BIZ_CD")
	private String bizCd;
	
	@Column(name="EMP_TYPE")
	private String empType;
	
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
	
	@Column(name="RETIRE_YN")
	private String retireYn;
	
	@Column(name="STATUS")
	private String status;
	
	@Transient
	private String userId;
	
	@Transient
	private String userNm;
	
	@Transient
	private String organization;
	
	@Transient
	private String organizationNo;
	
	public String getUserId() {
		return this.getEmpNo();
	}
	
	public String getUserNm() {
		return this.getEmpNm();
	}
	
	public Employee(String empNo) {
		this.empNo = empNo;
	}
	
}
