package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sys.dto.MenuDto;
import net.ecbank.fwk.admin.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.sys.entity.Menu;
import net.ecbank.fwk.admin.sys.repository.MenuRepository;

@Service
public class MenuManageService {
	
	@Autowired
	private MenuRepository menuRep;
	
	
	public List<MenuTreeDto> searchMenuListByTree(){
		
		List list = menuRep.searchMenuListByTree();
				
		return list;
	}
	
	public Menu searchMenuInfo(Long menuNo) {
		return menuRep.findOneByMenuNo(menuNo);
	}
	
	public void saveMenuInfo(MenuDto menuDto) {
		
		Menu program = new Menu(menuDto);
		
		if(menuDto.getModMenuNo() == menuDto.getMenuNo()) {
			menuRep.save(program);
		}else {
			if(menuDto.getModMenuNo() == 0 ) {
				menuRep.save(program);
			}else {
				menuRep.save(program);
				menuRep.delete(new Menu(menuDto.getMenuNo()));
			}
		}
		
	}
	
}
