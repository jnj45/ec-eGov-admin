package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sys.dao.MenuMapper;
import net.ecbank.fwk.admin.sys.dto.MenuDto;
import net.ecbank.fwk.admin.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.sys.entity.Menu;
import net.ecbank.fwk.admin.sys.repository.MenuRepository;

@Service
public class MenuManageService {
	
	@Autowired
	private MenuRepository menuRep;
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuTreeDto> searchMenuListByTree(){
		
		List<MenuTreeDto> list = menuMapper.selectMenuTreeList();
				
		return list;
	}
	
	public Menu searchMenuInfo(Long menuNo) {
		return menuRep.findOneByMenuNo(menuNo);
	}
	
	public void saveMenuInfo(MenuDto menuDto) {
		
		Menu menu = new Menu(menuDto);
		
		if(menuDto.getModMenuNo() == menuDto.getMenuNo()) {
			menuRep.save(menu);
		}else {
			if(menuDto.getModMenuNo() == 0 ) {
				menuRep.save(menu);
			}else {
				menuRep.save(menu);
				menuRep.delete(new Menu(menuDto.getMenuNo()));
			}
		}
		
	}
	
	public void deleteMenuInfo(MenuDto menuDto) {
		
		Menu menu = new Menu(menuDto.getModMenuNo());
		
		menuRep.delete(menu);
		
	}
	
}
