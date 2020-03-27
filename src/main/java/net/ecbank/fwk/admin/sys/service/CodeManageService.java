package net.ecbank.fwk.admin.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.entity.CodeDetail;
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
	public List<CodeGroup> searchCodeGrpList(CodeGroup codeGroup) {
		
		List<CodeGroup> list = null;
		
		list = codeGrpRepImpl.searchCodeGroup(codeGroup);
		
		return list;
	}
	
	@Transactional
	public List<CodeDetail> searchCodeDetailList(CodeGroup codeGroup) {
		
		List<CodeDetail> list = codeDetailRep.findByCodeGrp(codeGroup);
		
		return list;
	}
	
	
}
