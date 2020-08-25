package net.ecbank.fwk.admin.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * 로그인 사용자 정보
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
public class UserDetailHelper {
	
	/**
	 * 로그인 id 조회
	 * @return
	 */
	public static String getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = userDetails.getUsername();
		return username;
	}
}
