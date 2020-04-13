package net.ecbank.fwk.admin.manage.sys.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.manage.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.manage.sys.service.MenuAuthManageService;

@RestController
@RequestMapping("/sys")
public class MenuAuthManageRestController {
	
	@Autowired
	private MenuAuthManageService menuAuthManageService;
	
	@PostMapping("/searchMenuAuthTreeList")
	public List<MenuTreeDto> searchMenuAuthTreeList(@Valid MenuTreeDto menuTreeDto){
		
		List<MenuTreeDto> list = null;
		
		try {
			System.out.println("====================== 그리드 조회");
			list = menuAuthManageService.searchMenuAuthListByTree(menuTreeDto.getAuthCode());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
