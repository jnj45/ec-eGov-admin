package net.ecbank.fwk.admin.manage.sys.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.manage.sys.dto.BatchResultDto;
import net.ecbank.fwk.admin.manage.sys.dto.QBatchResultDto;
import net.ecbank.fwk.admin.manage.sys.entity.QBatchResult;

import static net.ecbank.fwk.admin.manage.sys.entity.QBatchResult.batchResult;
import static org.springframework.util.StringUtils.hasText;

/**
 * 
 * 배치 결과 관리 Repository
 * 
 * @author I21362
 * @since 2020. 6. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 6. 16. I21362 - 최초 작성.
 * </pre>
 */
@RequiredArgsConstructor
public class BatchResultRepositoryImpl {
	
	private static final Logger log = LoggerFactory.getLogger(BatchResultRepositoryImpl.class);
	
	private final JPAQueryFactory queryFactory;
	
	/**
	 * 배치결과 목록조회
	 * @param batchResultDto
	 * @return
	 */
	public Page<BatchResultDto> searchBatchResultList(BatchResultDto batchResultDto, Pageable pageable){
		QueryResults<BatchResultDto> results = queryFactory
			.select(new QBatchResultDto(batchResult))
			.from(new QBatchResult("batchResult"))
			.where(
					batchIdLike(batchResultDto.getBatchId()),
					batchBeginDateBetween(batchResultDto.getBatchBeginFromDay(), batchResultDto.getBatchBeginToDay()),
					batchStatusCodeEq(batchResultDto.getStatusCode())
					)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(batchResult.batchExecuteId.desc())
			.fetchResults();
		
		List<BatchResultDto> list = results.getResults();
		long totalCnt = results.getTotal();
		
		return new PageImpl<>(list, pageable, totalCnt);
	}
	
	private BooleanExpression batchIdLike(String batchId) {
		return hasText(batchId) ? batchResult.batchId.contains(batchId) : null;
	}
	
	private BooleanExpression batchBeginDateBetween(String batchBeginFromDay, String batchBeginToDay) {
		LocalDateTime from = LocalDateTime.parse(batchBeginFromDay+"T00:00:00");
		LocalDateTime to = LocalDateTime.parse(batchBeginToDay+"T23:59:59");
		log.debug("from:{}, to:{}", from, to);
		
		return batchResult.batchBeginDate.between(from, to);
	}
	
	private BooleanExpression batchStatusCodeEq(String statusCode) {
		return hasText(statusCode) ? batchResult.statusCode.eq(statusCode) : null;
	}
}


