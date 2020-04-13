package net.ecbank.fwk.admin.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.ecbank.fwk.admin.common.entity.AdminProperties;
import net.ecbank.fwk.admin.console.repository.AdminPropertiesRepository;

@SpringBootTest
@Transactional
class AdminPropertiesRepositoryTest {
	
	@Autowired
	AdminPropertiesRepository adminPropertiesRepository; 
	
	@Test
	void testFindByPropKeyLike() {
//		adminPropertiesRepository.save(new AdminProperties("aaa1", "aaa1", null));
//		adminPropertiesRepository.save(new AdminProperties("aaa2", "aaa2", null));
//		adminPropertiesRepository.save(new AdminProperties("bbb", "aaa", null));
		
		List<AdminProperties> findByPropKeyLike = adminPropertiesRepository.findProperties("aaa");
		for (AdminProperties adminProperties : findByPropKeyLike) {
			System.out.println(adminProperties);
		}
	}

}
