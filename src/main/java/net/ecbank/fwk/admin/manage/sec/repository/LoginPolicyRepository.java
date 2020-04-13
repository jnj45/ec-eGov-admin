package net.ecbank.fwk.admin.manage.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sec.entity.LoginPolicy;

public interface LoginPolicyRepository extends JpaRepository<LoginPolicy, String> {

}
