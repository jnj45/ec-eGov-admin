package net.ecbank.fwk.admin.manage.sec.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthInfoDto extends CommonDto{
	
	private String authCode;
	
	private String authNm;
	
	private String authDesc;
	
	private String createDate;
	
	private String modAuthCode;
	
	private String coCd;
	
	private String deptCd;
	
	private String userId;
	
	private String userType;
	
	private String regYn;
	
	private String orgRegYn;
	
	private Long seq;
	
	private List<AuthInfoDto> saveList;
	
	private List<AuthInfoDto> deleteList;
	
	private List<String> orgRoleList;
	
	private List<String> changeRoleList;
}
