package net.ecbank.fwk.admin.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CodeDetailKey implements Serializable {
	private String codeGrp;
	private String code; 
}
