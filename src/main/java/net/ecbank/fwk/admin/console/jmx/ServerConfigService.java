package net.ecbank.fwk.admin.console.jmx;

import java.util.List;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.common.entity.AdminProperties;
import net.ecbank.fwk.admin.console.service.AdminPropertiesService;

/**
 * 어플리케이션 서버의 환경설정을 JMX로 통해 런타임 반영 처리
 * @author LYJ
 *
 */
@Service
public class ServerConfigService {
	
	private final Logger log = LoggerFactory.getLogger(ServerConfigService.class);
	
	private final String JMX_URL_PROP_KEY = "jmx.service.url";
	private final String JMX_MBEAN_NAME = "ec-eGov:name=ServerConfigManageService";
	
	@Autowired
	AdminPropertiesService adminPropertiesService;
	
	public void reloadRolesAndUrlMapping() throws Exception{
		try {
			jmxCall("reloadRolesAndUrlMapping");
		}catch (Exception e) {
			log.error("어플리케이션 서버 URL접근제한정보 갱신 오류", e);
		}
	
	}
	
	public void reloadProperties() throws Exception{
		try {
			jmxCall("reloadProperties");
		}catch (Exception e) {
			log.error("어플리케이션 DB프로퍼티 정보 갱신 오류", e);
		}
	}
	
	private void jmxCall(String method) throws Exception{
		
		List<AdminProperties> findProperties = adminPropertiesService.findProperties(JMX_URL_PROP_KEY);
		
		for (AdminProperties adminProperties : findProperties) {
			String serviceUrl = adminProperties.getPropVal();
			
			JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
			try(JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceURL, null)){
				MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
				ObjectName mbeanName = new ObjectName(JMX_MBEAN_NAME);
				ServerConfigMBean mBeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, ServerConfigMBean.class, true);
				
				if(method.equals("reloadProperties")) {
					mBeanProxy.realodProperties();
				}
				
				if(method.equals("reloadRolesAndUrlMapping")) {
					mBeanProxy.reloadRolesAndUrlMapping();
				}
				
			}catch(Exception e) {
				throw e;
			}
		}
		
	}
}
