package net.ecbank.fwk.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);
		
//		http.authorizeRequests()
//				.anyRequest().permitAll()  //임시설정.
//				//.antMatchers("/", "/hello", "/actuator/**/**").permitAll()
//				//.mvcMatchers("/", "/info", "/signup").permitAll()
//				//.mvcMatchers("/admin").hasRole("ADMIN")
//				//.anyRequest().authenticated()
//				;
		
		//모든 요청을 허용하더라도 post는 403 에러가 난다. csrf를 disable해야함
		http.csrf().disable();  
		
		//http.exceptionHandling().accessDeniedPage("/accessDenied");
		
//		http.formLogin()
//			.loginPage("/login")
//			.defaultSuccessUrl("/index")
//			.permitAll();
		
//		http.httpBasic();
		
		//remember-me
//		http.rememberMe()
//			.userDetailsService(accountService)
//			.key("remember-me")
//			;
		//익명 principal 커스트마이징
//		http.anonymous().principal("annoymousUser");
		
//		http.logout()
//			.logoutUrl("/logout") //default: /logout
//			.logoutSuccessUrl("/index")
//			//.deleteCookies(cookieNamesToClear)
//			;
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().mvcMatchers("/favicon.ico");
////		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	//in-memory user 사용
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user").password("{noop}1234").roles("USER").and()
//			.withUser("admin").password("{noop}admin").roles("ADMIN");
//	}
}
