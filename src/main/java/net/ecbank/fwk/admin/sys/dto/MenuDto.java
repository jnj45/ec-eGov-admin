package net.ecbank.fwk.admin.sys.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.cmm.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuDto extends CommonDto {
	
	private String menuNm;
	private String programFileNm;
	private long menuNo;
	private long modMenuNo;
	private long upperMenuNo;
	private int menuOrder;
	private String menuDesc;
	private String relateImagePath;
	private String relateImageNm;
	private String url;
	private String newYn;
	private String regYn;
	private String authCode;
	
	private List<MenuDto> saveList;
	
	private List<MenuDto> deleteList;
	
}
