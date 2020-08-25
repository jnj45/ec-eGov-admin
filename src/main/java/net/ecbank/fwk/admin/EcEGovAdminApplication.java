package net.ecbank.fwk.admin;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.querydsl.jpa.impl.JPAQueryFactory;

import net.ecbank.fwk.admin.util.UserDetailHelper;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = {"net.ecbank.fwk.admin.manage.sys.dao","net.ecbank.fwk.admin.manage.sec.dao"})
public class EcEGovAdminApplication {
	
	public static void main(String[] args) {
		
		String profile = System.getProperty("spring.profiles.active");
			
		if(profile == null) {
            System.setProperty("spring.profiles.active", "local");
        }
		
		SpringApplication.run(EcEGovAdminApplication.class, args);
	}
	
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAware<String>() {
			
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of(UserDetailHelper.getUserId());
			}
			
		};
	}
	
	/*@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}*/
}
