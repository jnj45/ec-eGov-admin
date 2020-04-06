package net.ecbank.fwk.admin.sec.entity;

import java.util.List;

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

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="COMTNEMPLYRSCRTYESTBS")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@IdClass(UserAuthRelKey.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class UserAuthRel {
	
	@Id
	@Column(name="SCRTY_DTRMN_TRGET_ID")
	private String userId;
	
	@Id
	@Column(name="MBER_TY_CODE", columnDefinition ="char")
	private String userType;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_CODE")
	private AuthInfo authInfo;
	
	public UserAuthRel(String userId, String userType, String authCode) {
		
		this.userId = userId;
		this.userType = userType;
		
		this.authInfo = new AuthInfo();
		authInfo.setAuthCode(authCode);
		
	}
}
