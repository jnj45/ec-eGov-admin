package net.ecbank.fwk.admin.manage.sec.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.manage.sec.entity.LoginPolicy;
import net.ecbank.fwk.admin.manage.user.entity.Employee;
import net.ecbank.fwk.admin.manage.user.entity.VendorUser;

@Data
public class LoginPolicyDto extends CommonDto {
	
	//사용자 구분
	private String userGbn;
	
	//사용자 기본정보
	private String userId;
	private String userNm;
	private String organizationNo;
	private String organization;
	private String deptNm;
	private String email;
	
	//정책 정보
	private String ipInfo;
	private String duplicatePermmitYn;
	private String limitYn;
	private String createdBy;
	private String lastModifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	
	private List<LoginPolicyDto> saveList;
	
	private List<LoginPolicyDto> deleteList;
	
	public LoginPolicyDto() {
		
	}
	
	@QueryProjection
	public LoginPolicyDto(Employee employee, LoginPolicy loginPolicy) {
		
		this.userId = employee.getEmpNo();
		this.userNm = employee.getEmpNm();
		this.deptNm = employee.getDeptNm();
		this.email = employee.getEmail();
		
		if(loginPolicy !=null) {
			this.ipInfo = loginPolicy.getIpInfo();
			this.duplicatePermmitYn = loginPolicy.getDuplicatePermmitYn();
			this.limitYn = loginPolicy.getLimitYn();
			this.createdBy = loginPolicy.getCreatedBy();
			this.lastModifiedBy = loginPolicy.getLastModifiedBy();
			this.createdDate = loginPolicy.getCreatedDate();
			this.lastModifiedDate = loginPolicy.getLastModifiedDate();
		}
	}
	
	@QueryProjection
	public LoginPolicyDto(VendorUser vendorUser, LoginPolicy loginPolicy) {
		
		this.userId = vendorUser.getUserId();
		this.userNm = vendorUser.getUserNm();
		this.deptNm = vendorUser.getDeptNm();
		this.email = vendorUser.getEmail();
		this.organization = vendorUser.getVendor().getVendorNm();
		
		if(loginPolicy !=null) {
			this.ipInfo = loginPolicy.getIpInfo();
			this.duplicatePermmitYn = loginPolicy.getDuplicatePermmitYn();
			this.limitYn = loginPolicy.getLimitYn();
			this.createdBy = loginPolicy.getCreatedBy();
			this.lastModifiedBy = loginPolicy.getLastModifiedBy();
			this.createdDate = loginPolicy.getCreatedDate();
			this.lastModifiedDate = loginPolicy.getLastModifiedDate();
		}
	}
}
