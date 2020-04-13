package net.ecbank.fwk.admin.manage.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.ecbank.fwk.admin.manage.sys.dto.MenuTreeDto;

@Mapper
public interface MenuMapper {
	
	public List<MenuTreeDto> selectMenuTreeList();
	
	public List<MenuTreeDto> selectMenuAuthTreeList(String authCode);
	
}
