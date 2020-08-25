package net.ecbank.fwk.admin.manage.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.manage.sys.dto.BatchResultDto;
import net.ecbank.fwk.admin.manage.sys.entity.BatchResult;
import net.ecbank.fwk.admin.manage.sys.repository.BatchResultRepositoryImpl;

/**
 * 
 * 배치 관리 Service
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
@Service
public class BatchManageService {
	
	@Autowired
	private BatchResultRepositoryImpl batchResultRepositoryImpl;
	
	/**
	 * 배치결과조회
	 * @param batchResultDto
	 * @return
	 */
	public Page<BatchResultDto> searchBatchResultList(BatchResultDto batchResultDto){
		PageRequest pr = PageRequest.of(batchResultDto.getPage()-1, batchResultDto.getRows(), Sort.by(Direction.DESC, "batchExecuteId")); 
		return batchResultRepositoryImpl.searchBatchResultList(batchResultDto, pr);
	}
	
}
