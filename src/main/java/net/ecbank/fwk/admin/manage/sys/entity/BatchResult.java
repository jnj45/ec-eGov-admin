package net.ecbank.fwk.admin.manage.sys.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 배치 실행결과 ENTITY
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EF_BATCH_RESULT")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class BatchResult{
	
	@Id
	@Column(name="BATCH_EXEC_ID", columnDefinition="bigint")
	private Long batchExecuteId;
	
	@Column(name="BATCH_ID")
	private String batchId;
	
	@Column(name="BATCH_BGN_DT")
	private LocalDateTime batchBeginDate;
	
	@Column(name="BATCH_END_DT")
	private LocalDateTime batchEndDate;
	
	@Column(name="ERROR_INFO", columnDefinition = "nvarchar") //columnDefinition = "ntext"
	private String errorInfo;
	
	@Column(name="STTUS_CD",columnDefinition="char")
	private String statusCode;
	
	@Column(name="TOTAL_DATA_CNT")
	private Long totalDataCount;
	
	@Column(name="ERROR_DATA_CNT")
	private Long errorDataCount;
	
	@Column(name="SUCCESS_DATA_CNT")
	private Long successDataCount;
	
	@CreatedBy
	@Column(updatable = false, name="CRT_ID")
	private String createdBy;
	
	@LastModifiedBy
	@Column(name="MOD_ID")
	private String lastModifiedBy;
	
	@CreatedDate
	@Column(updatable = false, name="CRT_DT")
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name="MOD_DT")
	private LocalDateTime lastModifiedDate;
}
