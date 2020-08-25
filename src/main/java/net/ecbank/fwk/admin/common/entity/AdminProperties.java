package net.ecbank.fwk.admin.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 관리자콘솔 Application의 프로퍼티
 * @author LYJ
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
//@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name="EA_SYS_PROPS")
@TableGenerator(name="AdminProperties_ID_GENERATOR", table="EF_DOC_NO_SEQ", pkColumnValue="EA_SYS_PROPS_ID", pkColumnName="TABLE_NAME", valueColumnName="NEXT_ID", allocationSize=1)
public class AdminProperties extends BaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="AdminProperties_ID_GENERATOR")
	@Column(name="PROP_ID",columnDefinition="bigint")
	private Long propId;
	
	@Column(name="PROP_KEY")
	private String propKey;
	
	@Column(name="PROP_VAL")
	private String propVal;
	
	@Column(name="PROP_DESC")
	private String propDesc;
	
	@Column(name="USE_YN" ,columnDefinition="char")
	private String useYn;
	
	public AdminProperties(String propKey, String propVal, String propDesc) {
		this.propKey = propKey;
		this.propVal = propVal;
		this.propDesc = propDesc;
		this.useYn = "Y";
	}
}
