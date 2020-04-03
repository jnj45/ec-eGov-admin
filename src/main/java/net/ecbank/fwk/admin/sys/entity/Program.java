package net.ecbank.fwk.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.ecbank.fwk.admin.sys.dto.ProgramDto;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="COMTNPROGRMLIST")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class Program {
	
	@Id
	@Column(name="PROGRM_FILE_NM")
	private String programNm;
	
	@Column(name="PROGRM_STRE_PATH")
	private String programStorePath;
	
	@Column(name="PROGRM_KOREAN_NM")
	private String programKorNm;
	
	@Column(name="PROGRM_DC")
	private String programDesc;
	
	@Column(name="URL")
	private String url;
	
	public Program(String programNm) {
		this.programNm = programNm;
	}
	
	public Program(ProgramDto programDto) {
		
		this.programNm = programDto.getModProgramNm();
		this.programKorNm = programDto.getProgramKorNm();
		this.programStorePath = programDto.getProgramStorePath();
		this.programDesc = programDto.getProgramDesc();
		this.url = programDto.getUrl();
		
	}
	
}
