package net.ecbank.fwk.admin.manage.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuAuthRelKey implements Serializable {
	
	private String menuInfo;
	private String authInfo;
	
}
