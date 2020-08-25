package net.ecbank.fwk.admin.manage.sys.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.manage.sys.entity.BatchResult;

/**
 * 
 * 배치 결과 dto
 * 
 * @author I21362
 * @since 2020. 6. 4.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 6. 4. I21362 - 최초 작성.
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BatchResultDto extends CommonDto{
	
	private Long batchExecuteId;
	private String batchId;
	private LocalDateTime batchBeginDate;
	private LocalDateTime batchEndDate;
	private String errorInfo;
	private String statusCode;
	private Long totalDataCount;
	private Long errorDataCount;
	private Long successDataCount;
	
	private String batchBeginFromDay;
	private String batchBeginToDay;
	
	private List<BatchResultDto> searchList;
	
	public BatchResultDto() {
	}
	
	@QueryProjection
	public BatchResultDto(BatchResult batchResult) {
		this.batchExecuteId   = batchResult.getBatchExecuteId();         
		this.batchId          = batchResult.getBatchId();         
		this.batchBeginDate   = batchResult.getBatchBeginDate();  
		this.batchEndDate     = batchResult.getBatchEndDate();    
		this.errorInfo        = batchResult.getErrorInfo();       
		this.statusCode       = batchResult.getStatusCode();      
		this.totalDataCount   = batchResult.getTotalDataCount();  
		this.errorDataCount   = batchResult.getErrorDataCount();  
		this.successDataCount = batchResult.getSuccessDataCount();
	}
}
