package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sys.dao.MenuMapper;
import net.ecbank.fwk.admin.sys.dto.MenuTreeDto;

@Service
public class MenuAuthManageService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuTreeDto> searchMenuAuthListByTree(String authCode){
		
		List<MenuTreeDto> list = menuMapper.selectMenuAuthTreeList(authCode);
				
		return list;
	}
	
}
