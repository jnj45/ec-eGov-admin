package net.ecbank.fwk.admin.manage.log.repository;

import static net.ecbank.fwk.admin.manage.log.entity.QLoginLog.loginLog;
import static net.ecbank.fwk.admin.manage.sys.entity.QBatchResult.batchResult;
import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.manage.log.dto.QLoginLogDto;
import net.ecbank.fwk.admin.manage.log.entity.QLoginLog;
import net.ecbank.fwk.admin.manage.log.dto.LoginLogDto;

@RequiredArgsConstructor
public class LoginLogRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public Page<LoginLogDto> searchLoginLogList(LoginLogDto searchDto, Pageable pageable){
		
		QueryResults<LoginLogDto> results = queryFactory
				.select(new QLoginLogDto(loginLog))
				.from(new QLoginLog("loginLog"))
				.where(
						hasText(searchDto.getConectId()) ? loginLog.conectId.contains(searchDto.getConectId()) : null,
						hasText(searchDto.getConectIp()) ? loginLog.conectIp.contains(searchDto.getConectIp()) : null,
						hasText(searchDto.getConectMthd()) ? loginLog.conectMthd.trim().eq(searchDto.getConectMthd()) : null,
						hasText(searchDto.getErrorOccrrncAt()) ? loginLog.errorOccrrncAt.eq(searchDto.getErrorOccrrncAt()) : null,
						hasText(searchDto.getCoCode()) ? loginLog.coCode.eq(searchDto.getCoCode()) : null,
						accessBeginDateBetween(searchDto.getAccessDateBgn(), searchDto.getAccessDateEnd())
					  )
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(loginLog.createDate.desc())
				.fetchResults();
			
			List<LoginLogDto> list = results.getResults();
			long totalCnt = results.getTotal();
							
			return new PageImpl<>(list, pageable, totalCnt);
		
	}
	
	private BooleanExpression accessBeginDateBetween(String accessBeginFromDay, String accessBeginToDay) {
		LocalDateTime from = LocalDateTime.parse(accessBeginFromDay+"T00:00:00");
		LocalDateTime to = LocalDateTime.parse(accessBeginToDay+"T23:59:59");
		
		return loginLog.createDate.between(from, to);
	}
}
