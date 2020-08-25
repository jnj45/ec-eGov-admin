package net.ecbank.fwk.admin.util;

import org.springframework.context.ApplicationContext;

/**
 * 
 * 스프링 빈 찾기.
 * 
 * @author LYJ
 * @since 2020. 5. 15.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 5. 15. I21362 - 최초 작성.
 * </pre>
 */
public class BeanUtils {
    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}


