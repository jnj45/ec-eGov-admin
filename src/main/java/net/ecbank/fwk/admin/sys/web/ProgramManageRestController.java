package net.ecbank.fwk.admin.sys.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.cmm.dto.Response;
import net.ecbank.fwk.admin.sys.dto.ProgramDto;
import net.ecbank.fwk.admin.sys.entity.Program;
import net.ecbank.fwk.admin.sys.service.ProgramManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sys")
public class ProgramManageRestController {
	
	@Autowired
	private ProgramManageService programManageService;
	
	@PostMapping("/programList")
	public List<ProgramDto> codeGrpList(@RequestBody ProgramDto programDto) {
		
		List<Program> list = programManageService.searchProgramList(programDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveProgram")
	public ProgramDto saveProgram(@RequestBody ProgramDto programDto) {
		
		System.out.println("save size : " + programDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			programManageService.saveProgram(programDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		programDto.setResponse(res);
		
		return programDto;
	}
	
	@PostMapping("/deleteProgram")
	public ProgramDto deleteProgram(@RequestBody ProgramDto programDto) {
		
		Response res = new Response();
		
		try {
			programManageService.deleteProgram(programDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		programDto.setResponse(res);
		
		return programDto;
	}
	
	private List<ProgramDto> convertToDtoList(List<Program> programList) {
	    return programList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private ProgramDto convertToDto(Program program) {
		ProgramDto programDto = ModelMapperUtils.getModelMapper().map(program, ProgramDto.class);
		
		programDto.setModProgramNm(program.getProgramNm());
		
	    return programDto;
	}
}
