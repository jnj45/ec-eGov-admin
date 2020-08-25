package net.ecbank.fwk.admin.manage.sec.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserAuthDto extends CommonDto {
	
	private String coCd;
	private String userGbn;
	
	private String userId;
	private String userNm;
	private String organizationNo;
	private String organization;
	private String deptNm;
	private String email;
	private String userCls;
	private String orgnNm;
	
	private int page;
	private int totalPage;
	private int pageSize;
	private int rows;
	private int nextPage;
	
	public UserAuthDto(String userId,String userNm, String userCls, String orgnNm, String email, String coCd) {
		this.userId = userId;
		this.userNm = userNm;
		this.userCls = userCls;
		this.orgnNm = orgnNm;
		this.email = email;
		this.coCd = coCd;
	}
}
