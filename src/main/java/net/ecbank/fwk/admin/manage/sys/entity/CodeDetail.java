package net.ecbank.fwk.admin.manage.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table(name="EF_CODE")
@IdClass(CodeDetailKey.class)
@ToString(exclude = {"codeGrp"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class CodeDetail extends BaseEntity {
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CODE_ID")
	private CodeGroup codeGrp;
	
	/*@Column(name="CODE_ID")
	private String codeGrp;
	*/
	
	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="CODE_NM", columnDefinition ="nvarchar")
	private String codeNm;
	
	@Column(name="CODE_DC", columnDefinition ="nvarchar")
	private String codeDesc;
	
	@Column(name="USE_AT", columnDefinition ="char")
	private String useYn;
	
	//추가 컬럼
	@Column(name="SORT", columnDefinition ="bigint") 
	private int sort;
	
	@Column(name="CODE_ENG_NM", columnDefinition ="nvarchar")
	private String codeEngNm;
	
	@Column(name="ATTR1", columnDefinition ="nvarchar")
	private String attr1;
	
	@Column(name="ATTR2", columnDefinition ="nvarchar")
	private String attr2;
	
	@Column(name="ATTR3", columnDefinition ="nvarchar")
	private String attr3;
	
	@Transient
	private String codeGroupCd;
	
	@Transient
	private String modCode;
	
	public String getCodeGroupCd(){
		return codeGrp.getCodeGrp();
	}
	
	public String getModCode(){
		return this.getCode();
	}
	
	public CodeDetail(String codeGrp, String code, String codeNm,String codeDesc, String useYn,String codeEngNm,int sort,String attr1,String attr2,String attr3) {
		this.codeGrp = new CodeGroup();
		this.codeGrp.setCodeGrp(codeGrp);
		this.code = code;
		this.codeNm = codeNm;
		this.codeDesc = codeDesc;
		this.useYn = useYn;
		this.codeEngNm = codeEngNm;
		this.sort = sort;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
	}
	
	public CodeDetail(String codeGrp, String code) {
		this.codeGrp = new CodeGroup();
		this.codeGrp.setCodeGrp(codeGrp);
		this.code = code;
	}
	
	public CodeDetail(String codeGrp) {
		this.codeGrp = new CodeGroup();
		this.codeGrp.setCodeGrp(codeGrp);
	}
}
