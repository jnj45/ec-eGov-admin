package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.entity.CodeGroup;
import net.ecbank.fwk.admin.sys.repository.CodeDetailRepository;
import net.ecbank.fwk.admin.sys.repository.CodeGroupRepository;
import net.ecbank.fwk.admin.sys.repository.CodeGroupRepositoryImpl;

@Service
public class CodeManageService {
	
	@Autowired
	private CodeGroupRepository codeGrpRep;
	
	@Autowired
	private CodeGroupRepositoryImpl codeGrpRepImpl;
	
	@Autowired
	private CodeDetailRepository codeDetailRep;
	
	@Transactional
	public List<CodeGroup> selectCodeGroupList() {
		
		return codeGrpRep.findAll();
	}
	
	@Transactional
	public List<CodeGroup> findBycodeGrp(CodeGroup codeGroup) {
		
		List<CodeGroup> list = null;
		
		list = codeGrpRepImpl.searchCodeGroup(codeGroup);
		
		System.out.println("bio1215 = " + list);
		
		return list;
	}
	
}
