package net.ecbank.fwk.admin.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.sys.entity.CodeGroup;
import net.ecbank.fwk.admin.sys.service.CodeManageService;

@RestController
@RequestMapping("/sys")
public class CodeManageRestController {
	
	@Autowired
	private CodeManageService codeMngService;
  	
	@GetMapping("/codeGrpAllList")
	public List<CodeGroup> selectCodeGrpAllList() {
		
		List<CodeGroup> list = codeMngService.selectCodeGroupList();
		
		System.out.println("bio1215 : "+list.size());
		
		for(int i=0;i<list.size();i++) {
			CodeGroup cg = list.get(i);
			
			System.out.println("bio1215 obj : " + cg );
			
		}
		
		return list;
	}
	
	@PostMapping("/codeGrpList")
	public List<CodeGroup> codeGrpList(@RequestBody CodeGroup codeGroup) {
		
		List<CodeGroup> list = codeMngService.findBycodeGrp(codeGroup);
		
		return list;
	}
}
