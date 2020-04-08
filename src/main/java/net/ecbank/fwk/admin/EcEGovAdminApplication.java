package net.ecbank.fwk.admin;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.querydsl.jpa.impl.JPAQueryFactory;

@EnableJpaAuditing
@SpringBootApplication
public class EcEGovAdminApplication {

	public static void main(String[] args) {
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
				return Optional.of("AdminConsole");
			}
			
		};
	}
	
	/*@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}*/
}
