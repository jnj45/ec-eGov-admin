package net.ecbank.fwk.admin.manage.sec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import net.ecbank.fwk.admin.common.entity.BaseEntity;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="EF_USER_AUTH")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class UserAuthRel extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEQ")
	private Long seq;
	
	@Column(name="SCRTY_DTRMN_TRGET_ID")
	private String userId;
	
	@Column(name="CO_CD", columnDefinition ="char")
	private String coCd;
	
	@Column(name="MBER_TY_CODE", columnDefinition ="char")
	private String userType;
	
	@Column(name="USE_YN",columnDefinition="char")
	private String useYn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_CODE")
	private AuthInfo authInfo;
	
	public UserAuthRel(String userId, String userType, String authCode, Long seq,String coCd) {
		
		this.coCd = coCd;
		this.seq = seq;
		this.userId = userId;
		this.userType = userType;
		
		this.authInfo = new AuthInfo();
		authInfo.setAuthCode(authCode);
		
	}
}
