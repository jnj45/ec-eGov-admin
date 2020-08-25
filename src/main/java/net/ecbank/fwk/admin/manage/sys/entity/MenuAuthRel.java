package net.ecbank.fwk.admin.manage.sys.entity;

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
import net.ecbank.fwk.admin.manage.sec.entity.AuthInfo;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EF_AUTH_MENU")
//@ToString(exclude = {"codeClass", "codeDetails"})
@IdClass(MenuAuthRelKey.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class MenuAuthRel {
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MENU_NO")
	private Menu menuInfo;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_CODE")
	private AuthInfo authInfo;
	
	@Column(name="MAPNG_CREAT_ID")
	private String mappingCreateId;
	
	public MenuAuthRel(String menuNo,String authCode, String mappingCreateId) {
		
		this.menuInfo = new Menu(menuNo);
		this.authInfo = new AuthInfo(authCode);
		this.mappingCreateId = mappingCreateId;
		
	}
}
