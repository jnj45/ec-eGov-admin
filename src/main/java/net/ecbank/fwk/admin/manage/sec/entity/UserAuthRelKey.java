package net.ecbank.fwk.admin.manage.sec.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserAuthRelKey implements Serializable{
	
	private Long seq;
	private String authInfo;
	
}
