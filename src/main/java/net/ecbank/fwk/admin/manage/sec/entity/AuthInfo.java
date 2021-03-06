package net.ecbank.fwk.admin.manage.sec.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.dto.RoleInfoDto;
import net.ecbank.fwk.admin.manage.sys.entity.MenuAuthRel;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="EF_AUTH")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class AuthInfo {
	
	@Id
	@Column(name="AUTHOR_CODE")
	private String authCode;
	
	@Column(name="AUTHOR_NM")
	private String authNm;
	
	@Column(name="AUTHOR_DC")
	private String authDesc;
	
	@Column(name="AUTHOR_CREAT_DE", columnDefinition ="char")
	private String createDate;
	
	/*@JsonIgnore
	@OneToMany(mappedBy="authInfo",fetch=FetchType.EAGER)
	private List<RoleInfo> roleList = new ArrayList<RoleInfo>();*/

	@Transient
	private String regYn;
	
	@Transient
	private String orgRegYn;
	
	@Transient
	private Long seq;
	
	@JsonIgnore
	@OneToMany(mappedBy="authInfo",fetch=FetchType.LAZY)
	private List<AuthRoleRel> authRoleList = new ArrayList<AuthRoleRel>();
	
	@JsonIgnore
	@OneToMany(mappedBy="authInfo",fetch=FetchType.LAZY)
	private List<UserAuthRel> userAuthList = new ArrayList<UserAuthRel>();
	
	@JsonIgnore
	@OneToMany(mappedBy="authInfo",fetch=FetchType.LAZY)
	private List<MenuAuthRel> menuAuthList = new ArrayList<MenuAuthRel>();
	
	public AuthInfo(String authCode) {
		this.authCode = authCode;
	}
	
	public AuthInfo(String authCode, String authNm, String authDesc,String createDate) {
		this.authCode = authCode;
		this.authNm = authNm;
		this.authDesc = authDesc;
		this.createDate = createDate;
	}
	
	public AuthInfo(AuthInfoDto authInfoDto) {
		this.authCode = authInfoDto.getModAuthCode();
		this.authNm = authInfoDto.getAuthNm();
		this.authDesc = authInfoDto.getAuthDesc();
		this.createDate = authInfoDto.getCreateDate();
	}
	
	public AuthInfo(String authCode, String authNm, String authDesc,String createDate, String regYn, long seq) {
		this.authCode = authCode;
		this.authNm = authNm;
		this.authDesc = authDesc;
		this.createDate = createDate;
		
		if(regYn != null && !regYn.equals("")) {
			this.regYn = "Y";
			this.orgRegYn = "Y";
		} else {
			this.regYn = "";
			this.orgRegYn = "";
		}
		
		this.seq = seq;
	}
}
