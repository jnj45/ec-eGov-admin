package net.ecbank.fwk.admin.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuAuthRelKey implements Serializable {
	
	private long menuInfo;
	private String authInfo;
	
}
