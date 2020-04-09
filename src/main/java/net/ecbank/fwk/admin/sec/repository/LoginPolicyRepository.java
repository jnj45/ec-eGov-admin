package net.ecbank.fwk.admin.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sec.entity.LoginPolicy;

public interface LoginPolicyRepository extends JpaRepository<LoginPolicy, String> {

}
