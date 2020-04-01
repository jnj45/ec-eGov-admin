package net.ecbank.fwk.admin.sys.repository;

import static net.ecbank.fwk.admin.sys.entity.QCodeGroup.codeGroup;
import static net.ecbank.fwk.admin.sys.entity.QRoleInfo.roleInfo;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.sys.dto.RoleInfoDto;
import net.ecbank.fwk.admin.sys.entity.QRoleInfo;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;

@RequiredArgsConstructor
public class RoleInfoRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<RoleInfo> searchRoleInfoList(RoleInfoDto roleInfoDto){
		
		List<RoleInfo> results = queryFactory.selectFrom(new QRoleInfo("roleInfo"))
				.where(
						roleNmLike(roleInfoDto.getRoleNm()),
						roleCdEq(roleInfoDto.getRoleCode())
						)
				.fetch();
		
		return results;
	}
	
	private BooleanExpression roleNmLike(String roleNm) {
		return hasText(roleNm) ? roleInfo.roleNm.contains(roleNm) : null;
	}
	
	private BooleanExpression roleCdEq(String roleCd) {
		return hasText(roleCd) ? roleInfo.roleCode.eq(roleCd) : null;
	}
}
