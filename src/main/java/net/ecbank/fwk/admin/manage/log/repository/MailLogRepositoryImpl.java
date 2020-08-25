package net.ecbank.fwk.admin.manage.log.repository;

import static net.ecbank.fwk.admin.manage.log.entity.QMailLog.mailLog;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.manage.log.dto.MailLogDto;
import net.ecbank.fwk.admin.manage.log.dto.QMailLogDto;
import net.ecbank.fwk.admin.manage.log.entity.QMailLog;

@RequiredArgsConstructor
public class MailLogRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public Page<MailLogDto> searchMailLogList(MailLogDto searchDto, Pageable pageable){
		
		QueryResults<MailLogDto> results = queryFactory
				.select(new QMailLogDto(mailLog))
				.from(new QMailLog("mailLog"))
				.where(
						/*hasText(searchDto.getConectId()) ? loginLog.conectId.contains(searchDto.getConectId()) : null,
						hasText(searchDto.getConectIp()) ? loginLog.conectIp.contains(searchDto.getConectIp()) : null,
						hasText(searchDto.getConectMthd()) ? loginLog.conectMthd.trim().eq(searchDto.getConectMthd()) : null,
						hasText(searchDto.getErrorOccrrncAt()) ? loginLog.errorOccrrncAt.eq(searchDto.getErrorOccrrncAt()) : null,
						hasText(searchDto.getCoCode()) ? loginLog.coCode.eq(searchDto.getCoCode()) : null,
						accessBeginDateBetween(searchDto.getAccessDateBgn(), searchDto.getAccessDateEnd())*/
					  )
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(mailLog.crtDt.desc())
				.fetchResults();
			
			List<MailLogDto> list = results.getResults();
			long totalCnt = results.getTotal();
							
			return new PageImpl<>(list, pageable, totalCnt);
		
	}
	
	private BooleanExpression dateBetween(String fromDay, String toDay) {
		LocalDateTime from = LocalDateTime.parse(fromDay+"T00:00:00");
		LocalDateTime to = LocalDateTime.parse(toDay+"T23:59:59");
		
		return mailLog.crtDt.between(from, to);
	}
}
