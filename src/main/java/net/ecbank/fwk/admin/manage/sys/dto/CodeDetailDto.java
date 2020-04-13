package net.ecbank.fwk.admin.manage.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class CodeDetailDto extends CommonDto {
	
	private String codeGroupCd;
	
	private String code;
	
	private String modCode;
	
	private String codeNm;
	
	private String codeDesc;
	
	private String useYn;
	
	private int sort;
	
	private String codeEngNm;
	
	private String attr1;
	
	private String attr2;
	
	private String attr3;
	
	private List<CodeDetailDto> saveList;
	
	private List<CodeDetailDto> deleteList;
	
}
