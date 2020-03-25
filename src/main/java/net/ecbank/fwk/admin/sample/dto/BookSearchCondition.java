package net.ecbank.fwk.admin.sample.dto;

import lombok.Data;

@Data
public class BookSearchCondition {
	
	private String bookTitle;
	private Long totalPageLoe;
	private Long totalPageGoe;
	private String authorName;
}
