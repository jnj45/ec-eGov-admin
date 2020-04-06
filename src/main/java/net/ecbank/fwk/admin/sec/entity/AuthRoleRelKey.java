package net.ecbank.fwk.admin.sec.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthRoleRelKey implements Serializable {
		
	private String authInfo;
	private String roleInfo; 
	
}
