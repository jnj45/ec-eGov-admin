package net.ecbank.fwk.admin.manage.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="COMTCCMMNCODE")
@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class CodeGroup extends BaseEntity {
	
	@Id
	@Column(name="CODE_ID")
	private String codeGrp;
	
	@Column(name="CODE_ID_NM")
	private String codeGrpNm;
	
	@Column(name="CODE_ID_DC")
	private String codeGrpDesc;
	
	@Column(name="USE_AT", columnDefinition ="char")
	private String useYn;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CL_CODE")
	private CodeClass codeClass;
	
	@Transient
	private String modCodeGrp;
	
	@JsonIgnore
	@OneToMany(mappedBy="codeGrp",fetch=FetchType.LAZY)
	private List<CodeDetail> codeDetails = new ArrayList<CodeDetail>();
	
	public CodeGroup(String codeGrp, String codeGrpNm, String codeGrpDesc, String useYn) {
		this.codeGrp = codeGrp;
		this.codeGrpNm = codeGrpNm;
		this.codeGrpDesc = codeGrpDesc;
		this.useYn = useYn;
	}
	
	public CodeGroup(String codeGrp) {
		this.codeGrp = codeGrp;
	}
	
	public String getModCodeGrp(){
		return this.getCodeGrp();
	}
}
