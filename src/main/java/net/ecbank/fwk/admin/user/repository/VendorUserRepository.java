package net.ecbank.fwk.admin.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.user.entity.Employee;
import net.ecbank.fwk.admin.user.entity.VendorUser;

public interface VendorUserRepository extends JpaRepository<VendorUser, String> {
	
	List<Employee> findByUserIdContainingAndUserNmContaining(String userId,String userNm);
	
}
