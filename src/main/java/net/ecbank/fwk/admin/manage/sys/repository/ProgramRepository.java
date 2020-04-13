package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sys.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, String> {
	
	List<Program> findByProgramNmContainingAndProgramKorNmContaining(String programNm, String programKorNm);
	
}
