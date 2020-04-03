package net.ecbank.fwk.admin.sys.repository;

import static net.ecbank.fwk.admin.sys.entity.QAuthInfo.authInfo;
import static net.ecbank.fwk.admin.sys.entity.QAuthRoleRel.authRoleRel;
import static net.ecbank.fwk.admin.sys.entity.QRoleInfo.roleInfo;
import static net.ecbank.fwk.admin.sys.entity.QUserAuthRel.userAuthRel;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.sys.dto.AuthInfoDto;
import net.ecbank.fwk.admin.sys.dto.UserAuthDto;
import net.ecbank.fwk.admin.sys.entity.AuthInfo;
import net.ecbank.fwk.admin.sys.entity.QAuthInfo;
import net.ecbank.fwk.admin.sys.entity.QAuthRoleRel;
import net.ecbank.fwk.admin.sys.entity.QRoleInfo;
import net.ecbank.fwk.admin.sys.entity.QUserAuthRel;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;

@RequiredArgsConstructor
public class AuthInfoRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<AuthInfo> searchAuthInfoList(AuthInfoDto authInfoDto){
		
		List<AuthInfo> results = queryFactory.selectFrom(new QAuthInfo("authInfo"))
				.where(
						authNmLike(authInfoDto.getAuthNm()),
						authCdLike(authInfoDto.getAuthCode())
						)
				.fetch();
		
		return results;
	}
	
	public List<AuthInfo> searchUserAuthList(AuthInfoDto authInfoDto){
		
		List<AuthInfo> results = queryFactory.select(Projections.constructor(AuthInfo.class,
													authInfo.authCode,
													authInfo.authNm,
													authInfo.authDesc,
													authInfo.createDate,
													userAuthRel.authInfo.authCode.as("regYn")
													))
				.from(new QAuthInfo("authInfo"))
				.leftJoin(new QUserAuthRel("userAuthRel"))
				.on(authInfo.authCode.eq(userAuthRel.authInfo.authCode),userAuthRel.userId.eq(authInfoDto.getUserId()))
				.fetch();
		
		return results;
	}
	
	private BooleanExpression authNmLike(String authNm) {
		return hasText(authNm) ? authInfo.authNm.contains(authNm) : null;
	}
	
	private BooleanExpression authCdLike(String authCd) {
		return hasText(authCd) ? authInfo.authCode.contains(authCd) : null;
	}
}
