package net.ecbank.fwk.admin.intface.service;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 통합로그 API 연동 서비스
 * 
 * @author I21362
 * @since 2020. 6. 9.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 6. 9. I21362 - 최초 작성.
 * </pre>
 */
@Service
public class TotalLogApiService {

	private static final Logger log = LoggerFactory.getLogger(TotalLogApiService.class);
	
	@Value("${totalLog.api.url.auth}")
	String apiUrlAuth;
	
	@Value("${totalLog.api.key}")
	String apiKey;
	
	/**
	 * 권한변경 로그
	 * <pre>
	 *  api url : https://itapi.skchemicals.com/ro/api/v1/Log/Write
		•POST (Content-Type: application/json; charset=UTF-8)
		
		파라미터
		site string Y 해당 시스템의 Application Key (Application Master 정보 참조) 
		uri string Y 권한변경 발생한 해당 AbsoluteUri(전체 Uri 및 쿼리스트링 포함, 아래 예시 참조) 
		obj string Y 권한변경 목적(EX. 직무변경, 퇴사) 
		actor string Y 권한변경 접근자, 행위자 사번 
		ip string Y 권한변경 접근자 IP Address 
		custIdx string Y 권한변경 대상자(EX. 사번 등), N개의 다중 권한 처리 대비 Json Array 형식 
		bfRole string Y 기존 권한 (EX. 사용자(권한에 대한 세부내용을 나열..)) 
		afRole string  Y 변경된 권한 (EX. 관리자(권한에 대한 세부내용을 나열..), 퇴사..) 
		
		파라미터 데이타 예)
		{
		"site":"SIMS",
		"uri":"https://itsims.skchemicals.com/User/role/List",
		"obj":"업무변경 등",
		"actor":"993410",
		 "ip":"10.247.0.243",
		 "custIdx":["i21040","i21041"],                                   
		 "bfRole":"사용자",
		"afRole":"관리자"
		}
		응답데이타 예)
		{
		"ErrorMessage":"",
		"ErrorKey":"",
		"StatusCode":200,
		"RequestURL":""
		}
	 * </pre>
	 * @param param
	 */
	@Async
	public void sendAuthLog(Map<String, Object> param){
		
//		log.debug("totalLog.api.url={}", apiUrl);
//		log.debug("totalLog.api.key={}", apiKey);
		
		try {
			//연동시스템키
			param.put("site", apiKey);
			
			log.info("request data[map]={}", param);
			
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			
			log.info("apiUrlAuth={}", apiUrlAuth);
			HttpPost postRequest = new HttpPost(apiUrlAuth); //POST 메소드 URL 새성 
			postRequest.setHeader("Accept", 		"application/json");
			postRequest.setHeader("Connection", 	"keep-alive");
			postRequest.setHeader("Content-Type", 	"application/json");
			
			//json 메시지 입력
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(param);
			
			log.info("request data[json]={}", json);
			
			postRequest.setEntity(new StringEntity(json)); 

			HttpResponse response = client.execute(postRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				log.info("response data={}", body);
			} else {
				log.warn("response is error : {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e){
			log.error("통합로그 권한변경 전송 에러", e);
		}
	}

}
