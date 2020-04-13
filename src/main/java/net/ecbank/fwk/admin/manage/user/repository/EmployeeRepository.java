package net.ecbank.fwk.admin.manage.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.user.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	List<Employee> findByEmpNoContainingAndEmpNmContaining(String empNo,String empNm);
	
}
