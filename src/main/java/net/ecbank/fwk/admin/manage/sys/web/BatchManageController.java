package net.ecbank.fwk.admin.manage.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 배치 관리 컨트롤러
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
@Controller
@RequestMapping("/sys")
public class BatchManageController {
	
	/**
	 * 배치결과관리 페이지 이동
	 * @return
	 */
	@RequestMapping("/batchResultManage")
	public String batchResultManage() {
		return "sys/batchManage/batchResultManage";
	}
}
