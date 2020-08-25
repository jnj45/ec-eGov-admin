package net.ecbank.fwk.admin.sample.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.common.dto.Response;
import net.ecbank.fwk.admin.intface.service.TotalLogApiService;
import net.ecbank.fwk.admin.manage.sys.dto.BatchResultDto;
import net.ecbank.fwk.admin.util.UserDetailHelper;
import net.ecbank.fwk.admin.util.WebUtil;

@RestController
@RequestMapping("/sample")
public class SampleRestController {
	
	@Autowired
	TotalLogApiService totalLogApiService;
	
	/**
	 * 권한변경 통합로그 전송 예제
	 * @return
	 */
	@PostMapping("/sendTotalLogAuth")
	public Response sendTotalLogAuth(BatchResultDto batchResultDto) {
		Response res = new Response();
		
		//api 파라미터. 전송처리는 async로 처리되므로 세션과 관련된 정보는 호출 시 넘겨줘야 됨.
		Map<String,Object> apiParam = new HashMap<String,Object>();
		//로그인 처리 URL
		apiParam.put("uri",	WebUtil.getRequestBaseURL() + "/sec/userAuthManage");
		//권한변경 목적
		apiParam.put("obj",	"업무변경");
		//권한변경자
		apiParam.put("actor", UserDetailHelper.getUserId());
		//권한변경 목적
		apiParam.put("ip",	WebUtil.getClientIP());
		//권한변경 대상자
		apiParam.put("custIdx", new String[] {"I21367"});
		//변경전 권한
		apiParam.put("bfRole", "ROLE_EMP,ROLE_PUR");
		//변경후 권한
		apiParam.put("afRole", "ROLE_EMP,ROLE_PUR,ROLE_ADMIN");
		
		totalLogApiService.sendAuthLog(apiParam);
		
		return res;
	}
}
