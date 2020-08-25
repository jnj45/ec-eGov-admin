package net.ecbank.fwk.admin.manage.sec.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

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
@Table(name="EF_DEPT_AUTH")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class DeptAuthRel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEQ")
	private Long seq;
	
	@Column(name="CO_CD", columnDefinition ="char")
	private String coCd;
	
	@Column(name="DEPT_CD")
	private String deptCd;
	
	@Column(name="USE_YN",columnDefinition="char")
	private String useYn;
	
	@CreatedBy
	@Column(updatable = false, name="CRT_ID")
	private String createdBy;
	
	@CreatedDate
	@Column(updatable = false, name="CRT_DT")
	private LocalDateTime createdDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_CODE")
	private AuthInfo authInfo;
	
	public DeptAuthRel(String deptCd, String authCode, Long seq,String coCd) {
		
		this.seq = seq;
		this.coCd = coCd;
		this.deptCd = deptCd;
		
		this.authInfo = new AuthInfo();
		authInfo.setAuthCode(authCode);
		
	}
}
