package net.ecbank.fwk.admin.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.ecbank.fwk.admin.sys.dto.MenuTreeDto;

@Mapper
public interface MenuMapper {
	
	public List<MenuTreeDto> selectMenuTreeList();
	
	public List<MenuTreeDto> selectMenuAuthTreeList(String authCode);
	
}
