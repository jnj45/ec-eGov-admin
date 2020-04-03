package net.ecbank.fwk.admin.sec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sec.entity.AuthInfo;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, String> {
	
	List<AuthInfo> findByAuthNmContaining(String authNm);
	
}
