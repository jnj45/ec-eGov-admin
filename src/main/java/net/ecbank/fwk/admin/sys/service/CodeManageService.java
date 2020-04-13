package net.ecbank.fwk.admin.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.dto.CodeDetailDto;
import net.ecbank.fwk.admin.sys.dto.CodeGroupDto;
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
	
	@Transactional
	public void saveCodeGroup(List<CodeGroupDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			CodeGroupDto codeGrpDto = (CodeGroupDto) saveList.get(i);
			
			CodeGroup codeGrp = new CodeGroup(
										codeGrpDto.getModCodeGrp(), 
										codeGrpDto.getCodeGrpNm(), 
										codeGrpDto.getCodeGrpDesc(), 
										codeGrpDto.getUseYn()
										);
			
			if(codeGrpDto.getCodeGrp().equals(codeGrpDto.getModCodeGrp())) {
				codeGrpRep.save(codeGrp);
			}else {
				if(codeGrpDto.getCodeGrp() == null || codeGrpDto.getCodeGrp().equals("")) {
					codeGrpRep.save(codeGrp);
				}else {
					
					CodeGroup orgCodeGrp = new CodeGroup(
							codeGrpDto.getCodeGrp() 
							);
					
					List<CodeDetail> codeDtlList = codeDetailRep.findByCodeGrp(orgCodeGrp);
					
					codeGrpRep.save(codeGrp);
					
					for(int k=0;k<codeDtlList.size();k++) {
						CodeDetail nCodeDtl = codeDtlList.get(k);
						CodeDetail cDtl = new CodeDetail(
														codeGrpDto.getModCodeGrp(),
														nCodeDtl.getCode(),
														nCodeDtl.getCodeNm(),
														nCodeDtl.getCodeDesc(),
														nCodeDtl.getUseYn(),
														nCodeDtl.getCodeEngNm(),
														nCodeDtl.getSort(),
														nCodeDtl.getAttr1(),
														nCodeDtl.getAttr2(),
														nCodeDtl.getAttr3()
														);
						codeDetailRep.save(cDtl);
					}
					
					codeDetailRep.deleteByCodeGrp(new CodeGroup(codeGrpDto.getCodeGrp()));
					codeGrpRep.delete(new CodeGroup(codeGrpDto.getCodeGrp()));
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteCodeGroup(List<CodeGroupDto> deleteList) {

		for(int i=0;i<deleteList.size();i++) {
			CodeGroupDto codeGrpDto = (CodeGroupDto) deleteList.get(i);
			
            CodeGroup codeGrp = codeGrpRep.findOneBycodeGrp(codeGrpDto.getCodeGrp());
            
            codeDetailRep.deleteByCodeGrp(new CodeGroup(codeGrpDto.getCodeGrp()));
            
            codeGrpRep.delete(codeGrp);
			
		}
	}
	
	@Transactional
	public void saveCodeDetail(List<CodeDetailDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			CodeDetailDto codeDtlDto = (CodeDetailDto) saveList.get(i);
			
			CodeDetail codeDetail = new CodeDetail(
										codeDtlDto.getCodeGroupCd(), 
										codeDtlDto.getModCode(), 
										codeDtlDto.getCodeNm(),
										codeDtlDto.getCodeDesc(),
										codeDtlDto.getUseYn(),
										codeDtlDto.getCodeEngNm(),
										codeDtlDto.getSort(),
										codeDtlDto.getAttr1(),
										codeDtlDto.getAttr2(),
										codeDtlDto.getAttr3()
										);
			
			if(codeDtlDto.getCode().equals(codeDtlDto.getModCode())) {
				
				codeDetailRep.save(codeDetail);
			
			}else {
				if(codeDtlDto.getCode() == null || codeDtlDto.getCode().equals("")) {
					
					codeDetailRep.save(codeDetail);	
					
				}else {
					codeDetailRep.save(codeDetail);
					
					codeDetailRep.delete(new CodeDetail(codeDtlDto.getCodeGroupCd(),codeDtlDto.getCode()));
					
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteCodeDetail(List<CodeDetailDto> deleteList) {

		for(int i=0;i<deleteList.size();i++) {
			CodeDetailDto codeDtlDto = (CodeDetailDto) deleteList.get(i);
			
            CodeDetail codeDtl = new CodeDetail(codeDtlDto.getCodeGroupCd(), codeDtlDto.getCode());
			
            codeDetailRep.delete(codeDtl);
			
		}
	}
}
