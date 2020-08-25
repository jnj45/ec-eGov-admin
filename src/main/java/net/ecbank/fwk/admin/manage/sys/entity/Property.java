package net.ecbank.fwk.admin.manage.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.ecbank.fwk.admin.common.entity.BaseEntity;
import net.ecbank.fwk.admin.manage.sys.dto.PropertyDto;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EF_PROPERTY")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@TableGenerator(name="SystemProperties_ID_GENERATOR", table="EF_DOC_NO_SEQ", pkColumnValue="EF_SYS_PROPS_ID", pkColumnName="TABLE_NAME", valueColumnName="NEXT_ID", allocationSize=1)
public class Property extends BaseEntity {
	
	@Id
	@Column(name="PROP_ID")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="SystemProperties_ID_GENERATOR")
	private Long propId;
	
	@Column(name="PROFILE")
	private String profile;
	
	@Column(name="KEY")
	private String key;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="USE_YN",columnDefinition="char")
	private String useYn;
	
	public Property(Long propId) {
		this.propId = propId;
	}
	
	public Property(PropertyDto dto) {
		this.propId = dto.getPropId();
		this.profile = dto.getProfile();
		this.key = dto.getKey();
		this.value = dto.getValue();
		this.useYn = dto.getUseYn();
	}
}
