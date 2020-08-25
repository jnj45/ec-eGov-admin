package net.ecbank.fwk.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	/**
	 * 정적 리소스 맵핑 “/**”
	   application.properties 에 spring.mvc.static-path-pattern=/static/** 으로 설정하면 url 경로가 바뀜. /hello.html -> /static/hello.html
     ● 기본 리소스 위치
		○ classpath:/static
		○ classpath:/public
		○ classpath:/resources/
		○ classpath:/META-INF/resources
		○ 예) “/hello.html” => /static/hello.html
		○ spring.mvc.static-path-pattern: 맵핑 설정 변경 가능
		○ spring.mvc.static-locations: 리소스 찾을 위치 변경 가능
	 ● Last-Modified 헤더를 보고 304 응답을 보냄.
	 ● ResourceHttpRequestHandler가 처리함.
		○ WebMvcConfigurer의 addRersourceHandlers로 커스터마이징 할 수 있음
	 */
	
	@Value("${spring.webservice.intro}")
    private String introPage;
     
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 루트 (/) 로 접근 시 introPage로 이동하는 매핑 추가
        registry.addRedirectViewController("/", introPage);
    }
	
    @Bean
    public FilterRegistrationBean<SimpleXssFilter> getFilterRegistrationBean2(){
        FilterRegistrationBean<SimpleXssFilter> registrationBean = new FilterRegistrationBean<SimpleXssFilter>();
        registrationBean.setFilter(new SimpleXssFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*","/*/**","/*/*/**");    //filter를 거칠 url patterns
        return registrationBean;
    }
    
	@Override
	public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
		// 정적 리소스 디렉토리 추가
//		registry.addResourceHandler("/m/**")
//			.addResourceLocations("classpath:/m/")
//			.setCacheControl(CacheControl.maxAge(Duration.ofSeconds(20)));
		
//		registry.addResourceHandler("/webjars/**")
//			.addResourceLocations("classpath:/META-INF/resources/webjars/")
//			.setCacheControl(CacheControl.maxAge(Duration.ofSeconds(20)));
//		WebMvcConfigurer.super.addResourceHandlers(registry); //이 코드 없어도 됨.
	}
	
	/*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(htmlEscapingConverter());
    }

    private HttpMessageConverter<?> htmlEscapingConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }*/
}
