package net.ecbank.fwk.admin.manage.sec.repository;

import static net.ecbank.fwk.admin.manage.sec.entity.QAuthInfo.authInfo;
import static net.ecbank.fwk.admin.manage.sec.entity.QDeptAuthRel.deptAuthRel;
import static net.ecbank.fwk.admin.manage.sec.entity.QUserAuthRel.userAuthRel;
import static org.springframework.util.StringUtils.hasText;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.AuthInfo;
import net.ecbank.fwk.admin.manage.sec.entity.QAuthInfo;
import net.ecbank.fwk.admin.manage.sec.entity.QDeptAuthRel;
import net.ecbank.fwk.admin.manage.sec.entity.QUserAuthRel;

@RequiredArgsConstructor
public class AuthInfoRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<AuthInfo> searchAuthInfoList(AuthInfoDto authInfoDto){
		
		List<String> notInList = new ArrayList<String>();
		
		notInList.add("IS_AUTHENTICATED_ANONYMOUSLY");
		notInList.add("IS_AUTHENTICATED_FULLY");
		notInList.add("IS_AUTHENTICATED_REMEMBERED");
		
		List<AuthInfo> results = queryFactory.selectFrom(new QAuthInfo("authInfo"))
				.where(
						authNmLike(authInfoDto.getAuthNm()),
						authCdLike(authInfoDto.getAuthCode()),
						authInfo.authCode.notIn(notInList)
						)
				.fetch();
		
		return results;
	}
	
	public List<AuthInfo> searchUserAuthList(AuthInfoDto authInfoDto){
		
		List<String> notInList = new ArrayList<String>();
		
		notInList.add("IS_AUTHENTICATED_ANONYMOUSLY");
		notInList.add("IS_AUTHENTICATED_FULLY");
		notInList.add("IS_AUTHENTICATED_REMEMBERED");
		notInList.add("ROLE_ANONYMOUS");
		
		List<AuthInfo> results = queryFactory.select(Projections.constructor(AuthInfo.class,
													authInfo.authCode,
													authInfo.authNm,
													authInfo.authDesc,
													authInfo.createDate,
													userAuthRel.authInfo.authCode.as("regYn"),
													userAuthRel.seq
													))
				.from(new QAuthInfo("authInfo"))
				.leftJoin(new QUserAuthRel("userAuthRel"))
				.on(authInfo.authCode.eq(userAuthRel.authInfo.authCode),userAuthRel.userId.eq(authInfoDto.getUserId()),userAuthRel.useYn.eq("Y"),userAuthRel.coCd.eq(authInfoDto.getCoCd()))
				.where(
						authInfo.authCode.notIn(notInList)
						)
				.fetch();
		
		return results;
	}
	
	public List<AuthInfo> searchDeptAuthList(AuthInfoDto authInfoDto){
		
		List<String> notInList = new ArrayList<String>();
		
		notInList.add("IS_AUTHENTICATED_ANONYMOUSLY");
		notInList.add("IS_AUTHENTICATED_FULLY");
		notInList.add("IS_AUTHENTICATED_REMEMBERED");
		notInList.add("ROLE_ANONYMOUS");
		
		List<AuthInfo> results = queryFactory.select(Projections.constructor(AuthInfo.class,
													authInfo.authCode,
													authInfo.authNm,
													authInfo.authDesc,
													authInfo.createDate,
													deptAuthRel.authInfo.authCode.as("regYn"),
													deptAuthRel.seq
													))
				.from(new QAuthInfo("authInfo"))
				.leftJoin(new QDeptAuthRel("deptAuthRel"))
				.on(authInfo.authCode.eq(deptAuthRel.authInfo.authCode),deptAuthRel.deptCd.eq(authInfoDto.getDeptCd()),deptAuthRel.useYn.eq("Y"),deptAuthRel.coCd.eq(authInfoDto.getCoCd()))
				.where(
						authInfo.authCode.notIn(notInList)
						)
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
