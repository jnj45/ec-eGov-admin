package net.ecbank.fwk.admin.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 
 * Session scope의 attribute를 controller, service, dao 전 레이어에서 접근가능하도록 하는 유틸.
 * 
 * @author I21362
 * @since 2020. 6. 10.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 6. 10. I21362 - 최초 작성.
 * </pre>
 */
public class SessionScopeUtil {

	public static Object getAttribute(String name) {
        return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String name, Object value) {
//    	HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = req.getSession();
//        session.setAttribute(name, value);
        
        RequestContextHolder.getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String name) {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
    
}
