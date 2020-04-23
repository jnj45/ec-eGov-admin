package net.ecbank.fwk.admin.manage.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class PropertyDto extends CommonDto {
	
	private Long propId;
	private String profile;
	private String key;
	private String value;
	private String propDesc;
	private String useYn;
	
	private List<PropertyDto> saveList;
	
	private List<PropertyDto> deleteList;
}
