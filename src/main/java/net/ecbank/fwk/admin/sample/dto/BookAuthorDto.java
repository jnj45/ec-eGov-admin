package net.ecbank.fwk.admin.sample.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BookAuthorDto {
	
	public long bookId;
	public String bookTitle;
	public long totalPage;
	public long authorId;
	public String authorName;
	
	@QueryProjection
	public BookAuthorDto(long bookId, String bookTitle, long totalPage, long authorId, String authorName) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.totalPage = totalPage;
		this.authorId = authorId;
		this.authorName = authorName;
	}
		
}
