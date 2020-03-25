package net.ecbank.fwk.admin.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public Page<BookAuthorDto> searchBook(BookSearchCondition cond, Pageable pageable){
		return bookRepository.searchBook(cond, pageable);
	}
}
