package net.ecbank.fwk.admin.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.entity.Book;
import net.ecbank.fwk.admin.sample.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * repository에서 dto로 반환처리 하는 경우
	 * @param cond
	 * @param pageable
	 * @return
	 */
	public Page<BookAuthorDto> searchBooksDto(BookSearchCondition cond, Pageable pageable){
		return bookRepository.searchBooksDto(cond, pageable);
	}
	
	/**
	 * service에서는 entity로 반환하고 controller에서 dto로 변환하는 경우.
	 * @param cond
	 * @param pageable
	 * @return
	 */
	public Page<Book> searchBooks(BookSearchCondition cond, Pageable pageable){
		return bookRepository.searchBooks(cond, pageable);
	}
	
	public Book findBook(Long bookId) {
		return bookRepository.findById(bookId).orElse(new Book());
	}
}
