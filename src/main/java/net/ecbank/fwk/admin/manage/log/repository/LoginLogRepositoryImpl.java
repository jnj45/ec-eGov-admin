package net.ecbank.fwk.admin.manage.log.repository;

import static net.ecbank.fwk.admin.manage.log.entity.QLoginLog.loginLog;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
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
						hasText(searchDto.getErrorOccrrncAt()) ? loginLog.errorOccrrncAt.eq(searchDto.getErrorOccrrncAt()) : null
					  )
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
			
			List<LoginLogDto> list = results.getResults();
			long totalCnt = results.getTotal();
							
			return new PageImpl<>(list, pageable, totalCnt);
		
	}
	
}
