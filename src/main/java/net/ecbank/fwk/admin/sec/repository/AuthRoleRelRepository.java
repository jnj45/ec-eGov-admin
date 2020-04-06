package net.ecbank.fwk.admin.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sec.entity.AuthRoleRel;

public interface AuthRoleRelRepository extends JpaRepository<AuthRoleRel, String> {
	
}
