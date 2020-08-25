package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sys.entity.BatchResult;

/**
 * 
 * 배치 결과 repository 인터페이스
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
public interface BatchResultRepository extends JpaRepository<BatchResult, Long> {
	
	List<BatchResult> findByBatchId(String batchId);

}
