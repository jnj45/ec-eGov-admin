package net.ecbank.fwk.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import net.ecbank.fwk.admin.console.jmx.ServerConfigMBean;


@SpringBootTest
class EcEGovAdminApplicationTests {

	//@Test
	void contextLoads() throws Exception {
		
		JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9875/jmxrmi"); //,service:jmx:rmi:///jndi/rmi://localhost:9875/server,"service:jmx:rmi://localhost:9875"
		JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceURL, null);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("ec-eGov:name=ServerConfigManageService");
        ServerConfigMBean mBeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, ServerConfigMBean.class, true);
        mBeanProxy.reloadRolesAndUrlMapping();
	}
	
	/**
	 * 전자정부 테이블 중 필요없는 테이블 삭제 스크립트 생성.
	 * 단, COMTECOPSEQ 는 지우지 말 것.
	 * @throws IOException
	 */
	@Test
	void findTableForDelete() throws IOException {
		List<String> deleteTables = new ArrayList<String>();
		
		File tableFile = new File("D:\\Dev\\eGovFrameDev-3.9.0-64bit\\workspace\\ec-eGov-admin\\script\\EGOV_ALL_TABLE_LIST.txt");
		List<String> readLines = FileUtils.readLines(tableFile, "UTF-8");
		
		for (String tableName : readLines) {
			if ("COMTECOPSEQ".equals(tableName)) {
				continue;
			}
			boolean isExist = false;
			//System.out.println(tableName);
			Iterator<File> queryFile = FileUtils.iterateFiles(new File("D:\\Dev\\eGovFrameDev-3.9.0-64bit\\workspace\\ec-eGov\\src\\main\\resources\\egovframework\\mapper\\com"), new String[]{"xml"}, true);
			Iterator<File> queryFile2 = FileUtils.iterateFiles(new File("D:\\Dev\\eGovFrameDev-3.9.0-64bit\\workspace\\ec-eGov\\src\\main\\resources\\egovframework\\spring\\com"), new String[]{"xml"}, true);
			
			while (queryFile.hasNext() && !isExist) {
				File file = (File) queryFile.next();
				String query = FileUtils.readFileToString(file, "UTF-8");
				if (StringUtils.containsIgnoreCase(query, tableName)) {
					isExist = true;
					//System.out.println(tableName);
				}
			}
			while (queryFile2.hasNext() && !isExist) {
				File file = (File) queryFile2.next();
				String query = FileUtils.readFileToString(file, "UTF-8");
				if (StringUtils.containsIgnoreCase(query, tableName)) {
					isExist = true;
					//System.out.println(tableName);
				}
			}
			if (!isExist && !ArrayUtils.contains(ArrayUtils.toArray(deleteTables),tableName)) {
				deleteTables.add(tableName);
			}
		}
		System.out.println("총:"+deleteTables.size()+"개");
		for (String string : deleteTables) {
			System.out.println("DROP TABLE "+string+" CASCADE CONSTRAINTS;");
		}
	}

}
