package net.ecbank.fwk.admin.manage.sec.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import net.ecbank.fwk.admin.common.dto.CommonDto;

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
	
}
