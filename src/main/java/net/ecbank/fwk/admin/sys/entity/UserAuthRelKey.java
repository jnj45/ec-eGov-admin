package net.ecbank.fwk.admin.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserAuthRelKey implements Serializable{
	
	private String userId;
	private String userType;
	private String authInfo;
	
}
