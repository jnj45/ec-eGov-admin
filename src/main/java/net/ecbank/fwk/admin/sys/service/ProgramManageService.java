package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.dto.ProgramDto;
import net.ecbank.fwk.admin.sys.entity.Program;
import net.ecbank.fwk.admin.sys.repository.ProgramRepository;

@Service
public class ProgramManageService {
	
	@Autowired
	private ProgramRepository programRep;
	
	public List<Program> searchProgramList(ProgramDto programDto){
		
		List<Program> list = programRep.findByProgramNmContainingAndProgramKorNmContaining(programDto.getProgramNm(), programDto.getProgramKorNm());
		
		return list;
	}
	
	@Transactional
	public void saveProgram(List<ProgramDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			ProgramDto programDto = (ProgramDto) saveList.get(i);
			
			Program program = new Program(programDto);
			
			if(programDto.getProgramNm().equals(programDto.getModProgramNm())) {
				programRep.save(program);
			}else {
				if(programDto.getProgramNm() == null || programDto.getProgramNm().equals("")) {
					programRep.save(program);
				}else {
					programRep.save(program);
					programRep.delete(new Program(programDto.getProgramNm()));
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteProgram(List<ProgramDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			ProgramDto programDto = (ProgramDto) deleteList.get(i);
			
			programRep.delete(new Program(programDto.getProgramNm()));
			
		}
	}
	
}
