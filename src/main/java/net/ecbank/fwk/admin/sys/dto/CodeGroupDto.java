package net.ecbank.fwk.admin.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CodeGroupDto extends CommonDto{
	
	private String codeGrp;
	
	private String codeGrpNm;
	
	private String codeGrpDesc;
	
	private String useYn;
	
	private String modCodeGrp;
	
	private List<CodeGroupDto> saveList;
	
	private List<CodeGroupDto> deleteList;
}
