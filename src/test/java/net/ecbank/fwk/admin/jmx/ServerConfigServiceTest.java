package net.ecbank.fwk.admin.jmx;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.ecbank.fwk.admin.console.jmx.ServerConfigService;

@SpringBootTest
class ServerConfigServiceTest {
	
	@Autowired
	ServerConfigService serverConfigService;
	
	@Test
	void testReloadRolesAndUrlMapping() throws Exception {
		serverConfigService.reloadRolesAndUrlMapping();
	}

}
