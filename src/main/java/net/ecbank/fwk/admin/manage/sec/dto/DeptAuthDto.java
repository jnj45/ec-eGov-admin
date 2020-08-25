package net.ecbank.fwk.admin.manage.sec.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class DeptAuthDto extends CommonDto {
	
	private String coCd;
	
	private String deptCd;
	
}
