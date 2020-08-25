package net.ecbank.fwk.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.ecbank.fwk.admin.console.service.UserService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
	
	
	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);
		
//		http.authorizeRequests()
//				.anyRequest().permitAll()  //임시설정.
//				//.antMatchers("/", "/hello", "/actuator/**/**").permitAll()
//				//.mvcMatchers("/", "/info", "/signup").permitAll()
//				//.mvcMatchers("/admin").hasRole("ADMIN")
//				//.anyRequest().authenticated()
//				;
		
		//모든 요청을 허용하더라도 post는 403 에러가 난다. csrf를 disable해야함
		//http.csrf().disable();  
		
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
//	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().mvcMatchers("/favicon.ico");
////		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}*/
	
	//in-memory user 사용
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user").password("{noop}1234").roles("USER").and()
//			.withUser("admin").password("{noop}admin").roles("ADMIN");
//	}
	
	@Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "/static/img/**", "/static/images/**", "/static/fonts/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		 http.authorizeRequests()
         // 페이지 권한 설정
		 .antMatchers("/*/*").hasRole("ADMIN")
         .antMatchers("/*").permitAll()
		 .anyRequest().authenticated()
		 .and().csrf().disable(); 
		 
		 // 로그인 설정
		 http.formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/loginSuc")
         .and()
         .logout()
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/logoutSuc")
         .invalidateHttpSession(true)
         .and()
         // 403 예외처리 핸들링
         .exceptionHandling().accessDeniedPage("/userDenied");
		 
    }
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
