package net.ecbank.fwk.admin.sec.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
import net.ecbank.fwk.admin.sec.dto.RoleInfoDto;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="COMTNROLEINFO")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class RoleInfo {
	
	@Id
	@Column(name="ROLE_CODE")
	private String roleCode;
	
	@Column(name="ROLE_NM")
	private String roleNm;
	
	@Column(name="ROLE_PTTRN")
	private String rolePattern;
	
	@Column(name="ROLE_DC")
	private String roleDesc;
	
	@Column(name="ROLE_TY")
	private String roleType;
	
	@Column(name="ROLE_SORT")
	private String roleSort;
	
	@Column(name="ROLE_CREAT_DE", columnDefinition ="char")
	private String createDate;
	
	@Transient
	private String roleRegYn;
	
	@ManyToOne(optional = false)
	@JoinTable(name = "COMTNAUTHORROLERELATE",
				joinColumns = @JoinColumn(name = "ROLE_CODE"),
				inverseJoinColumns = @JoinColumn(name = "AUTHOR_CODE")
			  )
	private AuthInfo authInfo;
	
	@JsonIgnore
	@OneToMany(mappedBy="roleInfo",fetch=FetchType.LAZY)
	private List<AuthRoleRel> authRoleRelList = new ArrayList<AuthRoleRel>();
	
	public RoleInfo(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public RoleInfo(String roleCode, String roleNm, String rolePattern,String roleDesc, String roleType,String roleSort,String createDate) {
		this.roleCode = roleCode;
		this.roleNm = roleNm;
		this.rolePattern = rolePattern;
		this.roleDesc = roleDesc;
		this.roleType = roleType;
		this.roleSort = roleSort;
		this.createDate = createDate;
	}
	
	public RoleInfo(RoleInfoDto roleInfoDto) {
		this.roleCode = roleInfoDto.getModRoleCode();
		this.roleNm = roleInfoDto.getRoleNm();
		this.rolePattern = roleInfoDto.getRolePattern();
		this.roleDesc = roleInfoDto.getRoleDesc();
		this.roleType = roleInfoDto.getRoleType();
		this.roleSort = roleInfoDto.getRoleSort();
	}
	
	public RoleInfo(String roleCode, String roleNm, String rolePattern,String roleDesc, String roleType,String roleSort,String createDate, String roleRegYn) {
		this.roleCode = roleCode;
		this.roleNm = roleNm;
		this.rolePattern = rolePattern;
		this.roleDesc = roleDesc;
		this.roleType = roleType;
		this.roleSort = roleSort;
		this.createDate = createDate;
		this.roleRegYn = roleRegYn;
	}
}
