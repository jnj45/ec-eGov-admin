package net.ecbank.fwk.admin.console.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.console.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	List<User> findByUserIdContainingAndUserNmContaining(String userId, String userNm);
	
	User findOneByUserId(String userId);
	
}
