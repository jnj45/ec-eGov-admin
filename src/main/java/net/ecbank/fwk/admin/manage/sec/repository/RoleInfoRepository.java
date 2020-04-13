package net.ecbank.fwk.admin.manage.sec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sec.entity.RoleInfo;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, String> {
	
	List<RoleInfo> findByRoleNmContaining(String roleNm);
	
}
