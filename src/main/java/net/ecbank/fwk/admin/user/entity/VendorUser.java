package net.ecbank.fwk.admin.user.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="EF_VENDOR_USER")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class VendorUser extends BaseEntity {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NM")
	private String userNm;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VENDOR_CD")
	private Vendor vendor;
	
	@Column(name="DEPT_NM")
	private String deptNm;
	
	@Column(name="MOBILE")
	private String mobile;
	
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
	
	@Column(name="ADM_USER_YN")
	private String admUserYn;
	
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
	
	@Column(name="STATUS")
	private String status;

	@Transient
	private String Organization;
	
	public String getOrganization() {
		return this.vendor.getVendorNm();
	}
	
}
