package net.ecbank.fwk.admin.sys.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="COMTNAUTHORROLERELATE")
@IdClass(AuthRoleRelKey.class)
//@ToString(exclude = {"authInfo"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) 
public class AuthRoleRel {
	
	@Id
	@Column(name="AUTHOR_CODE")
	private String authCode;
	
	@Id
	@JsonIgnore
	@Column(name="ROLE_CODE")
	private String roleCode;
	
	@CreatedDate
	@Column(updatable = false, name="CREAT_DT")
	private LocalDateTime createdDate;
}
