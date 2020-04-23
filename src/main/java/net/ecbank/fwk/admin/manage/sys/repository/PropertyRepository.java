package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sys.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	List<Property> findByKeyContaining(String key);
	
}
