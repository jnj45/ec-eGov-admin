package net.ecbank.fwk.admin.console.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserDto extends CommonDto {
	
	private String userId;
	private String modUserId;
	private String userNm;
	private String deptNm;
	private String mobile;
	private String email;
	private String tel;
	private String password;
	private int pwdFailCnt;
	private LocalDateTime lastPwdChgDt;
	private LocalDateTime lastLoginDt;
	private String useYn;
	
	private List<UserDto> saveList;
	
	private List<UserDto> deleteList;
	
}
