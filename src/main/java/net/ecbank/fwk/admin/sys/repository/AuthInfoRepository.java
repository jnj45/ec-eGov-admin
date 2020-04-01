package net.ecbank.fwk.admin.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sys.entity.AuthInfo;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, String> {
	
	List<AuthInfo> findByAuthNmContaining(String authNm);
	
}
