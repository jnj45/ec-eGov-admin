package net.ecbank.fwk.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.entity.AdminProperties;
import net.ecbank.fwk.admin.repository.AdminPropertiesRepository;

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
		return adminPropertiesRepository.findByPropKeyStartingWith(propKey);
	}
	
	public AdminProperties findProperty(String propKey) {
		return adminPropertiesRepository.findByPropKey(propKey);
	}
}
