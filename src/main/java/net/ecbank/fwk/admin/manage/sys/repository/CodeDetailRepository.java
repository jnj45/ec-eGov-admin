package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sys.entity.CodeDetail;
import net.ecbank.fwk.admin.manage.sys.entity.CodeGroup;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {
	
	List<CodeDetail> findByCodeGrp(CodeGroup codeGrp);
	
	void deleteByCodeGrp(CodeGroup codeGrp);
	
}
