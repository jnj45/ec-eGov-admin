package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sys.entity.CodeGroup;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
	
	List<CodeGroup> findBycodeGrp(String codeGrp);
	
	CodeGroup findOneBycodeGrp(String codeGrp);
	
	List<CodeGroup> findBycodeGrpAndCodeGrpNmContaining(String codeGrp, String codeGrpNm);
	
	List<CodeGroup> findByCodeGrpNmContaining(String codeGrpNm);
	
}
