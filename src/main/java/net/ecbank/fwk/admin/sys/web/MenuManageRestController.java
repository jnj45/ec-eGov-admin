package net.ecbank.fwk.admin.sys.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.cmm.dto.Response;
import net.ecbank.fwk.admin.sys.dto.MenuDto;
import net.ecbank.fwk.admin.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.sys.dto.ProgramDto;
import net.ecbank.fwk.admin.sys.entity.Menu;
import net.ecbank.fwk.admin.sys.service.MenuManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sys")
public class MenuManageRestController {
	
	@Autowired
	private MenuManageService menuManageService;
	
	@PostMapping("/searchMenuListByTree")
	public List<MenuTreeDto> searchMenuListByTree(){
		
		List<MenuTreeDto> list = menuManageService.searchMenuListByTree();
		
		return list;
	}
	
	@PostMapping("/searchMenuInfo")
	public MenuDto searchMenuInfo(@RequestBody MenuDto menu){
		
		return convertToDto(menuManageService.searchMenuInfo(menu.getMenuNo()));
	}
	
	@PostMapping("/saveMenuInfo")
	public MenuDto saveProgram(@RequestBody MenuDto menuDto) {
		
		Response res = new Response();
		
		try {
			
			menuManageService.saveMenuInfo(menuDto);
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		menuDto.setResponse(res);
		
		return menuDto;
	}
	
	@PostMapping("/deleteMenuInfo")
	public MenuDto deleteMenuInfo(@RequestBody MenuDto menuDto) {
		
		Response res = new Response();
		
		try {
			
			menuManageService.deleteMenuInfo(menuDto);
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		menuDto.setResponse(res);
		
		return menuDto;
	}
	
	private List<MenuDto> convertToDtoList(List<Menu> menuList) {
	    return menuList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private MenuDto convertToDto(Menu menu) {
		MenuDto menuDto = ModelMapperUtils.getModelMapper().map(menu, MenuDto.class);
		
	    return menuDto;
	}
}
