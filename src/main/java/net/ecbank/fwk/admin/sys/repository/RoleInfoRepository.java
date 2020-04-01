package net.ecbank.fwk.admin.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sys.entity.RoleInfo;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, String> {
	
	List<RoleInfo> findByRoleNmContaining(String roleNm);
	
}
