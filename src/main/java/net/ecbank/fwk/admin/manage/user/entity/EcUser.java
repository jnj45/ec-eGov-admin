package net.ecbank.fwk.admin.manage.user.entity;

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

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="EC_USER_VIEW")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class EcUser {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NM", columnDefinition="nvarchar")
	private String userNm;
	
	@Column(name="USER_CLS")
	private String userCls;
	 
	@Column(name="CO_CD", columnDefinition="char")
	private String coCd;
	
	@Column(name="USE_YN", columnDefinition="char")
	private String useYn;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ORGN_NM", columnDefinition="nvarchar")
	private String orgnNm;
	
}
