package net.ecbank.fwk.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.ecbank.fwk.admin.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="COMTCCMMNDETAILCODE")
@IdClass(CodeDetailKey.class)
@ToString(exclude = {"codeGrp"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class CodeDetail extends BaseEntity {
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_ID")
	private CodeGroup codeGrp;
	
	/*@Column(name="CODE_ID")
	private String codeGrp;
	*/
	
	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="CODE_NM")
	private String codeNm;
	
	@Column(name="CODE_DC")
	private String codeDesc;
	
	@Column(name="USE_AT", columnDefinition ="char")
	private String useYn;

	
}
