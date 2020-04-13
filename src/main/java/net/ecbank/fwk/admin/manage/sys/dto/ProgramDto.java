package net.ecbank.fwk.admin.manage.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProgramDto extends CommonDto {
	
	private String programNm;
	private String programStorePath;
	private String programKorNm;
	private String programDesc;
	private String url;
	private String modProgramNm;
	
	private List<ProgramDto> saveList;
	
	private List<ProgramDto> deleteList;
}
