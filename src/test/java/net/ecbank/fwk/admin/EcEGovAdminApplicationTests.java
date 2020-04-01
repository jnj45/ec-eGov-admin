package net.ecbank.fwk.admin;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.ecbank.fwk.admin.jmx.ServerConfigMBean;


@SpringBootTest
class EcEGovAdminApplicationTests {

	@Test
	void contextLoads() throws Exception {
		
		JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9875/jmxrmi"); //,service:jmx:rmi:///jndi/rmi://localhost:9875/server,"service:jmx:rmi://localhost:9875"
		JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceURL, null);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("ec-eGov:name=ServerConfigManageService");
        ServerConfigMBean mBeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, ServerConfigMBean.class, true);
        mBeanProxy.reloadRolesAndUrlMapping();
	}

}
