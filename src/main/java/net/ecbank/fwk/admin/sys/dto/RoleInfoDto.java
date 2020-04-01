package net.ecbank.fwk.admin.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleInfoDto extends CommonDto{
	
	private String roleCode;
	
	private String roleNm;
	
	private String rolePattern;
	
	private String roleDesc;
	
	private String roleType;
	
	private String roleSort;
	
	private String createDate;
	
	private String modRoleCode;
	
	private String roleRegYn;
	
	private List<RoleInfoDto> saveList;
	
	private List<RoleInfoDto> deleteList;
}
