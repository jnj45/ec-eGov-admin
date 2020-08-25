package net.ecbank.fwk.admin.console.jmx;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.common.entity.AdminProperties;
import net.ecbank.fwk.admin.console.service.AdminPropertiesService;

/**
 * 어플리케이션 서버의 환경설정 및 배치수행 등을 JMX로 통해 런타임 반영 처리
 * @author LYJ
 *
 */
@Service
public class ServerConfigService {
	
	private final Logger log = LoggerFactory.getLogger(ServerConfigService.class);
	
	private final String JMX_URL_PROP_KEY = "jmx.service.url";
	
	@Autowired
	AdminPropertiesService adminPropertiesService;
	
	@Autowired
	JmxService jmxService;
	
	/**
	 * URL접근제한정보 갱신
	 * @throws Exception
	 */
	public void reloadRolesAndUrlMapping() throws Exception{
		try {
			jmxCall("reloadRolesAndUrlMapping", null);
		}catch (Exception e) {
			log.error("어플리케이션 서버 URL접근제한정보 갱신 오류", e);
		}
	
	}
	/**
	 *  DB프로퍼티 정보 갱신
	 *  - 사용안함.
	 * @throws Exception
	 */
	public void reloadProperties() throws Exception{
		try {
			jmxCall("reloadProperties", null);
		}catch (Exception e) {
			log.error("어플리케이션 DB프로퍼티 정보 갱신 오류", e);
		}
	}
	
	/**
	 * 배치실행
	 * @param batchId
	 * @throws Exception
	 */
	public void executeBatch(String batchId) throws Exception{
		try {
			jmxCall("executeBatch", batchId);
		}catch (Exception e) {
			log.error("어플리케이션 배치 실행 오류", e);
		}
	}
	
	private void jmxCall(String method, Object param) throws Exception{
		
		List<AdminProperties> findProperties = adminPropertiesService.findProperties(JMX_URL_PROP_KEY);
		
		//jmx 서버별로 호출
		for (AdminProperties adminProperties : findProperties) {
			String serviceUrl = adminProperties.getPropVal();
			jmxService.callJmx(serviceUrl, method, param);
		}
	}
	
}
