package net.ecbank.fwk.admin.console.jmx;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 
 * JMX 메소드를 호출하는 서비스
 * 
 * @author I21362
 * @since 2020. 6. 8.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 6. 8. I21362 - 최초 작성.
 * </pre>
 */
@Service
public class JmxService {
	
	private final Logger log = LoggerFactory.getLogger(JmxService.class);
	
	private final String JMX_MBEAN_NAME = "ec-eGov:name=ServerConfigManageService";
	
	/**
	 * 비동기로 jmx 를 호출한다.
	 * 
	 * operation별로 정의한 인터페이스를 구현하는 각각의 jmxbean을 구현하거나,
	 * java refelection으로 메소드이름별로 proxy 메소드 호출을 자동 분리하면 좋지만
	 * 일단 하드코딩 처리함.
	 * 
	 * @param serviceUrl
	 * @param methodName
	 * @param param
	 */
	@Async
	public void callJmx(String serviceUrl, String methodName, Object param) {
		
		JMXConnector jmxc = null;
		
		try{
			JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
			jmxc = JMXConnectorFactory.connect(jmxServiceURL, null);

			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			ObjectName mbeanName = new ObjectName(JMX_MBEAN_NAME);
			ServerConfigMBean mBeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, ServerConfigMBean.class, true);
			
			if(methodName.equals("reloadProperties")) {
				mBeanProxy.realodProperties();
			}
			if(methodName.equals("reloadRolesAndUrlMapping")) {
				mBeanProxy.reloadRolesAndUrlMapping();
			}
			if(methodName.equals("executeBatch")) {
				mBeanProxy.executeBatch((String)param);
			}
			
		}catch(Exception e) {
			log.error("jmx call error", e);
		}finally {
			if (jmxc != null) {
				try {jmxc.close();}catch(Exception ignored) {log.warn("jmx connt closeing error!, {}", ignored.getMessage());}
			}
		}
	}
}
