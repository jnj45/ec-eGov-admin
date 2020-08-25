package net.ecbank.fwk.admin.manage.sec.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.manage.sec.dto.DeptTreeDto;
import net.ecbank.fwk.admin.manage.sec.service.DeptAuthManageService;

@RestController
@RequestMapping("/sec")
public class DeptAuthManageRestController {
	
	@Autowired
	private DeptAuthManageService deptAuthManageService;
	
	@PostMapping("/searchDeptAuthTreeList")
	public List<DeptTreeDto> searchMenuAuthTreeList(@RequestBody DeptTreeDto deptTreeDto){
		
		List<DeptTreeDto> list = null;
		
		try {
			System.out.println("====================== 그리드 조회");
			list = deptAuthManageService.searchDeptListByTree(deptTreeDto.getCoCd());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
