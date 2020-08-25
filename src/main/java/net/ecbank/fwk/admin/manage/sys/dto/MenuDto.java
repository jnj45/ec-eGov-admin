package net.ecbank.fwk.admin.manage.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuDto extends CommonDto {
	
	private String menuNm;
	private String menuEnNm;
	private String programFileNm;
	private String menuNo;
	private String modMenuNo;
	private String upperMenuNo;
	private int menuOrder;
	private String menuDesc;
	private String relateImagePath;
	private String relateImageNm;
	private String url;
	private String newYn;
	private String regYn;
	private String authCode;
	private String useYn;
	private String viewCoCd;
	
	private List<MenuDto> saveList;
	
	private List<MenuDto> deleteList;
	
}
