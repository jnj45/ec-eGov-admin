package net.ecbank.fwk.admin.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthRoleRelKey implements Serializable {
		
	private String authInfo;
	private String roleInfo; 
	
}
