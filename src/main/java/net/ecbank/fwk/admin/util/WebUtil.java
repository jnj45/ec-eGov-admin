package net.ecbank.fwk.admin.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {
	
	/**
     * 클라이언트(Client)의 IP주소를 구한다.
     * @param request
     * @return
     */
	public static String getClientIP( HttpServletRequest request){
        
        String ip = request.getHeader("X-Forwarded-For");
        
        if( StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
     
        return ip;
    }
	
	/**
	 * 클라이언트 ip
	 * @return
	 */
	public static String getClientIP(){
		return getClientIP(getHttpServletRequest());
	}
	
	/**
	 * 현재 쓰레드의 HttpServletRequest 객체를 구한다.
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 요청 URL의 base path (프로토콜+domain+port+contextpath) 를 구한다.
	 * @return
	 */
	public static String getRequestBaseURL() {
		HttpServletRequest req = getHttpServletRequest();
		return StringUtils.replace(req.getRequestURL().toString(), req.getRequestURI(), "");
	}
	
	/**
	 * ServletContext 객체를 참조한다.
	 * @return
	 */
	public static ServletContext getServletContext() {
		return getHttpServletRequest().getServletContext();
	}
}
