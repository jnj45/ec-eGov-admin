package net.ecbank.fwk.admin.cmm.dto;

import lombok.Data;

@Data
public class CommonDto{
	
	private int page;
	private int totalPage;
	private int pageSize;
	private int rows;
	private int nextPage;
	
	private Response response;
	
}
