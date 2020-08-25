package net.ecbank.fwk.admin.manage.sys.web;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.common.dto.Response;
import net.ecbank.fwk.admin.console.jmx.ServerConfigService;
import net.ecbank.fwk.admin.manage.sys.dto.BatchResultDto;
import net.ecbank.fwk.admin.manage.sys.entity.BatchResult;
import net.ecbank.fwk.admin.manage.sys.service.BatchManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

/**
 * 
 * 배치 관리 RestController
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
@RestController
@RequestMapping("/sys")
public class BatchManageRestController {
	
	private static final Logger log = LoggerFactory.getLogger(BatchManageRestController.class);
	
	@Autowired
	BatchManageService batchManageService;
	
	@Autowired
	ServerConfigService serverConfigService;
	
	/**
	 * 배치 결과 목록 검색
	 * @param batchResultDto
	 * @return
	 */
	@RequestMapping("/batchResultList")
	public Page<BatchResultDto> batchResultList(@RequestBody BatchResultDto batchResultDto){
		Page<BatchResultDto> page = batchManageService.searchBatchResultList(batchResultDto);
		return page;
	}
	
	/**
	 * 배치 실행
	 * @param batchResultDto
	 * @return
	 */
	@PostMapping("/executeBatch")
	public Response executeBatch(@RequestBody BatchResultDto batchResultDto) {
		Response res = new Response();
		
		log.info("executeBatch======================{}", batchResultDto);
		try {
			serverConfigService.executeBatch(batchResultDto.getBatchId());
		}catch(Exception e) {
			log.error("배치 실행 오류", e);
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		return res;
	}
	
	private List<BatchResultDto> convertToDtoList(List<BatchResult> batchResultList) {
	    return batchResultList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private BatchResultDto convertToDto(BatchResult batchResult) {
		BatchResultDto batchResultDto = ModelMapperUtils.getModelMapper().map(batchResult, BatchResultDto.class);
	    return batchResultDto;
	}
	
}
