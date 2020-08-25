package net.ecbank.fwk.admin.manage.sec.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.ecbank.fwk.admin.manage.sec.dto.DeptTreeDto;

@Mapper
public interface DeptMapper {
	
	public List<DeptTreeDto> selectDeptTreeList(String coCd);
	
}
