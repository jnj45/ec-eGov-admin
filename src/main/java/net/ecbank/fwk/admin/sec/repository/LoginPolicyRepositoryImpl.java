package net.ecbank.fwk.admin.sec.repository;

import static net.ecbank.fwk.admin.sec.entity.QLoginPolicy.loginPolicy;
import static net.ecbank.fwk.admin.sec.entity.QRoleInfo.roleInfo;
import static net.ecbank.fwk.admin.user.entity.QEmployee.employee;
import static net.ecbank.fwk.admin.user.entity.QVendorUser.vendorUser;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.sec.dto.LoginPolicyDto;
import net.ecbank.fwk.admin.sec.dto.QLoginPolicyDto;
import net.ecbank.fwk.admin.sec.entity.QLoginPolicy;
import net.ecbank.fwk.admin.user.entity.QEmployee;
import net.ecbank.fwk.admin.user.entity.QVendorUser;

@RequiredArgsConstructor
public class LoginPolicyRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<LoginPolicyDto> searchEmployeeLoginPolicyList(LoginPolicyDto loginPolicyDto){
		
		List<LoginPolicyDto> results = queryFactory.select(new QLoginPolicyDto(employee,loginPolicy))
				.from(new QEmployee("employee"))
				.leftJoin(new QLoginPolicy("loginPolicy"))
				.on(employee.empNo.eq(loginPolicy.userId))
				.where(
						empNmLike(loginPolicyDto.getUserNm()),
						empNoLike(loginPolicyDto.getUserId())
				)
				.fetch();
		
		return results;
	}
	
	public List<LoginPolicyDto> searchVendorLoginPolicyList(LoginPolicyDto loginPolicyDto){
		
		List<LoginPolicyDto> results = queryFactory.select(new QLoginPolicyDto(vendorUser,loginPolicy))
				.from(new QVendorUser("vendorUser"))
				.leftJoin(new QLoginPolicy("loginPolicy"))
				.on(vendorUser.userId.eq(loginPolicy.userId))
				.where(
						venUserNmLike(loginPolicyDto.getUserNm()),
						venUserIdLike(loginPolicyDto.getUserId())
				)
				.fetch(); 
		
		return results;
	}
	
	private BooleanExpression empNmLike(String userNm) {
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
	}
}
