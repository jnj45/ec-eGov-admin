package net.ecbank.fwk.admin.manage.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.UserAuthRel;
import net.ecbank.fwk.admin.manage.sys.dao.MenuMapper;
import net.ecbank.fwk.admin.manage.sys.dto.MenuDto;
import net.ecbank.fwk.admin.manage.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.manage.sys.entity.Menu;
import net.ecbank.fwk.admin.manage.sys.entity.MenuAuthRel;
import net.ecbank.fwk.admin.manage.sys.repository.MenuAuthRelRepository;
import net.ecbank.fwk.admin.manage.sys.repository.MenuRepository;

@Service
public class MenuManageService {
	
	@Autowired
	private MenuRepository menuRep;
	
	@Autowired
	private MenuAuthRelRepository menuAuthRelRep;
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuTreeDto> searchMenuListByTree(){
		
		List<MenuTreeDto> list = menuMapper.selectMenuTreeList();
				
		return list;
	}
	
	public Menu searchMenuInfo(String menuNo) {
		return menuRep.findOneByMenuNo(menuNo);
	}
	
	@Transactional
	public void saveMenuInfo(MenuDto menuDto) {
		
		Menu menu = new Menu(menuDto);
		
		if(menuDto.getModMenuNo().equals(menuDto.getMenuNo())) {
			menuRep.save(menu);
		}else {
			if(menuDto.getModMenuNo().equals("0")) {
				menuRep.save(menu);
			}else {
				menuRep.save(menu);
				menuRep.delete(new Menu(menuDto.getMenuNo()));
			}
		}
		
	}
	
	@Transactional
	public void deleteMenuInfo(MenuDto menuDto) {
		
		Menu menu = new Menu(menuDto.getModMenuNo());
		
		menuRep.delete(menu);
		
	}
	
	@Transactional
	public void saveMenuAuthRelation(MenuDto menuDto) {
		
		for(int i=0;i<menuDto.getSaveList().size();i++) {
			
			MenuDto lMenuDto = menuDto.getSaveList().get(i);
			
			MenuAuthRel menuAuthRel = new MenuAuthRel(lMenuDto.getMenuNo(),menuDto.getAuthCode(),"");
			
			if(lMenuDto.getRegYn().equals("Y")) {
				menuAuthRelRep.save(menuAuthRel);
			}else {
				menuAuthRelRep.delete(menuAuthRel);
			}
			
		}
		
	}
}
