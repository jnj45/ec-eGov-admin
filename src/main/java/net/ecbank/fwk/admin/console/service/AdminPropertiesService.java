package net.ecbank.fwk.admin.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.common.entity.AdminProperties;
import net.ecbank.fwk.admin.console.repository.AdminPropertiesRepository;

/**
 * 관리콘솔 App 프로퍼티 service
 * @author LYJ
 *
 */
@Service
public class AdminPropertiesService {
	
	@Autowired
	AdminPropertiesRepository adminPropertiesRepository;
	
	public List<AdminProperties> findProperties(String propKey){
		return adminPropertiesRepository.findProperties(propKey);
	}
	
	public AdminProperties findProperty(String propKey) {
		return adminPropertiesRepository.findByPropKey(propKey);
	}
}
