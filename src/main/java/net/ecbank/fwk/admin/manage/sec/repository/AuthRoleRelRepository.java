package net.ecbank.fwk.admin.manage.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sec.entity.AuthRoleRel;

public interface AuthRoleRelRepository extends JpaRepository<AuthRoleRel, String> {
	
}
