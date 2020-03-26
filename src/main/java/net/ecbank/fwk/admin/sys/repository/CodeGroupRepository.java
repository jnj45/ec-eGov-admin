package net.ecbank.fwk.admin.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sys.entity.CodeGroup;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
	
	CodeGroup findBycodeGrp(String codeGrp);
	
}
