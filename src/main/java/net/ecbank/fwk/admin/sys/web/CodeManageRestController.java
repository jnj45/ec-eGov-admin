package net.ecbank.fwk.admin.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.cmm.dto.Response;
import net.ecbank.fwk.admin.sys.dto.CodeDetailDto;
import net.ecbank.fwk.admin.sys.dto.CodeGroupDto;
import net.ecbank.fwk.admin.sys.entity.CodeDetail;
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
		
		List<CodeGroup> list = codeMngService.searchCodeGrpList(codeGroup);
		
		return list;
	}
	
	@PostMapping("/codeDetailList")
	public List<CodeDetail> codeDetailList(@RequestBody CodeGroup codeGroup) {
		
		List<CodeDetail> list = codeMngService.searchCodeDetailList(codeGroup);
		
		return list;
	}
	
	@PostMapping("/saveCodeGroup")
	public CodeGroupDto saveCodeGroup(@RequestBody CodeGroupDto cdGrpDto) {
		
		System.out.println("save size : " + cdGrpDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			codeMngService.saveCodeGroup(cdGrpDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		cdGrpDto.setResponse(res);
		
		return cdGrpDto;
	}
	
	@PostMapping("/deleteCodeGroup")
	public CodeGroupDto deleteCodeGroup(@RequestBody CodeGroupDto cdGrpDto) {
		
		Response res = new Response();
		
		try {
			codeMngService.deleteCodeGroup(cdGrpDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		cdGrpDto.setResponse(res);
		
		return cdGrpDto;
	}
	
	@PostMapping("/saveCodeDetail")
	public CodeDetailDto saveCodeDetail(@RequestBody CodeDetailDto cdDtlDto) {
		
		System.out.println("save size : " + cdDtlDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			System.out.println(cdDtlDto.getSaveList());
			codeMngService.saveCodeDetail(cdDtlDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		cdDtlDto.setResponse(res);
		
		return cdDtlDto;
	}
	
	@PostMapping("/deleteCodeDetail")
	public CodeDetailDto deleteCodeDetail(@RequestBody CodeDetailDto cdDtlDto) {
		
		Response res = new Response();
		
		try {
			codeMngService.deleteCodeDetail(cdDtlDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		cdDtlDto.setResponse(res);
		
		return cdDtlDto;
	}
}
