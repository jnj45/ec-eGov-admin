package net.ecbank.fwk.admin.console.jmx;

/**
 * JMX MBean들의 operation method 모음 인터페이스
 * @author LYJ
 *
 */
public interface ServerConfigMBean {
	
	/**
	 * spring security의 rolea and url 권한맵핑설정을 reaload하는 mbean 메소드
	 */
	public void reloadRolesAndUrlMapping();
}
