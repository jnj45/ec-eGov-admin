package net.ecbank.fwk.admin.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthInfoDto extends CommonDto{
	
	private String authCode;
	
	private String authNm;
	
	private String authDesc;
	
	private String createDate;
	
	private String modAuthCode;
	
	private List<AuthInfoDto> saveList;
	
	private List<AuthInfoDto> deleteList;
	
}
