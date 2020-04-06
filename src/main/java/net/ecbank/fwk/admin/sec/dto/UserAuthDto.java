package net.ecbank.fwk.admin.sec.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.cmm.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserAuthDto extends CommonDto {
	
	private String userGbn;
	
	private String userId;
	private String userNm;
	private String organizationNo;
	private String organization;
	private String deptNm;
	private String email;
	
}
