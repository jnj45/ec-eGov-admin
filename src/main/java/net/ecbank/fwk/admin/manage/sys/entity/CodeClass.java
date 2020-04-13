package net.ecbank.fwk.admin.manage.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.ecbank.fwk.admin.common.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="COMTCCMMNCLCODE")
@ToString(exclude = {"codeGroups"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class CodeClass extends BaseEntity {
	
	@Id
	@Column(name="CL_CODE", columnDefinition ="char")
	private String codeClassCd;
	
	@Column(name="CL_CODE_NM")
	private String codeClassNm;
	
	@Column(name="CL_CODE_DC")
	private String codeClassDesc;
	
	@Column(name="USE_AT", columnDefinition ="char")
	private String useYn;
	
	@JsonIgnore
	@OneToMany(mappedBy="codeClass",fetch=FetchType.LAZY)
	private List<CodeGroup> codeGroups;
	
	public CodeClass(String codeClassCd, String codeClassNm, String codeClassDesc, String useYn) {
		this.codeClassCd = codeClassCd;
		this.codeClassNm = codeClassNm;
		this.codeClassDesc = codeClassDesc;
		this.useYn = useYn;
	}
	
}
