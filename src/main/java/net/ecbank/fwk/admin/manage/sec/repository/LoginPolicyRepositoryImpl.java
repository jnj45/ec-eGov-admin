package net.ecbank.fwk.admin.manage.sec.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.manage.sec.dto.LoginPolicyDto;

@RequiredArgsConstructor
public class LoginPolicyRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<LoginPolicyDto> searchEmployeeLoginPolicyList(LoginPolicyDto loginPolicyDto){
		
		/*List<LoginPolicyDto> results = queryFactory.select(new QLoginPolicyDto(employee,loginPolicy))
				.from(new QEmployee("employee"))
				.leftJoin(new QLoginPolicy("loginPolicy"))
				.on(employee.empNo.eq(loginPolicy.userId))
				.where(
						empNmLike(loginPolicyDto.getUserNm()),
						empNoLike(loginPolicyDto.getUserId())
				)
				.fetch();*/
		
		return null;
	}
	
	public List<LoginPolicyDto> searchVendorLoginPolicyList(LoginPolicyDto loginPolicyDto){
		
		/*List<LoginPolicyDto> results = queryFactory.select(new QLoginPolicyDto(vendorUser,loginPolicy))
				.from(new QVendorUser("vendorUser"))
				.leftJoin(new QLoginPolicy("loginPolicy"))
				.on(vendorUser.userId.eq(loginPolicy.userId))
				.where(
						venUserNmLike(loginPolicyDto.getUserNm()),
						venUserIdLike(loginPolicyDto.getUserId())
				)
				.fetch(); */
		
		return null;
	}
	
	/*private BooleanExpression empNmLike(String userNm) {
		return hasText(userNm) ? employee.empNm.contains(userNm) : null;
	}
	
	private BooleanExpression empNoLike(String userId) {
		return hasText(userId) ? employee.empNo.contains(userId) : null;
	}
	
	private BooleanExpression venUserNmLike(String userNm) {
		return hasText(userNm) ? vendorUser.userNm.contains(userNm) : null;
	}
	
	private BooleanExpression venUserIdLike(String userId) {
		return hasText(userId) ? vendorUser.userId.contains(userId) : null;
	}*/
}
