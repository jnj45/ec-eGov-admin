package net.ecbank.fwk.admin.common.dto;

import lombok.Data;

@Data
public class Response {
	
	private String responseCd = "S";
	private String responseMsg;
	private String responseErrMsg;
	
}
