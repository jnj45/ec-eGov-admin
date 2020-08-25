package net.ecbank.fwk.admin.manage.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.manage.sec.dao.DeptMapper;
import net.ecbank.fwk.admin.manage.sec.dto.DeptTreeDto;

@Service
public class DeptAuthManageService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	public List<DeptTreeDto> searchDeptListByTree(String coCd){
		
		List<DeptTreeDto> list = deptMapper.selectDeptTreeList(coCd);
				
		return list;
	}
	
}
