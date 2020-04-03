package net.ecbank.fwk.admin.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.cmm.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class CodeDetailDto extends CommonDto {
	
	private String codeGroupCd;
	
	private String code;
	
	private String modCode;
	
	private String codeNm;
	
	private String codeDesc;
	
	private String useYn;
	
	private List<CodeDetailDto> saveList;
	
	private List<CodeDetailDto> deleteList;
	
}
