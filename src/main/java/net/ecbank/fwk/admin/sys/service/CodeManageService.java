package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.entity.CodeGroup;
import net.ecbank.fwk.admin.sys.repository.CodeDetailRepository;
import net.ecbank.fwk.admin.sys.repository.CodeGroupRepository;

@Service
public class CodeManageService {
	
	@Autowired
	private CodeGroupRepository codeGrpRep;
	
	@Autowired
	private CodeDetailRepository codeDetailRep;
	
	
	@Transactional
	public List<CodeGroup> selectCodeGroupList() {
		
		return codeGrpRep.findAll();
	}
	
	@Transactional
	public CodeGroup findBycodeGrp(String codeGrp) {
		
		CodeGroup cg = codeGrpRep.findBycodeGrp(codeGrp);
		
		System.out.println("bio1215 = " + cg);
		
		return cg;
	}
	
}
