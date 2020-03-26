package net.ecbank.fwk.admin.sample.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import net.ecbank.fwk.admin.sample.entity.Author;
import net.ecbank.fwk.admin.sample.entity.Book;

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
	
	@QueryProjection
	public BookAuthorDto(Book book) {
		this.bookId = book.getId();
		this.bookTitle = book.getTitle();
		this.totalPage = book.getTotalPage();
		this.authorId = book.getAuthor().getId();
		this.authorName = book.getAuthor().getName();
	}
	
	@QueryProjection
	public BookAuthorDto(Book book, Author author) {
		this.bookId = book.getId();
		this.bookTitle = book.getTitle();
		this.totalPage = book.getTotalPage();
		this.authorId = author.getId();
		this.authorName = author.getName();
	}
		
}
