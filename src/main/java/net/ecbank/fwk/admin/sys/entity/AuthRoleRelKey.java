package net.ecbank.fwk.admin.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthRoleRelKey implements Serializable {
		
	private String authCode;
	private String roleCode; 
	
}
