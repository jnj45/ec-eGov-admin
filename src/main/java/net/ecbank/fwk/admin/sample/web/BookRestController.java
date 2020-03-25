package net.ecbank.fwk.admin.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.service.BookService;

@RestController
@RequestMapping("/sample")
public class BookRestController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/books")
	public Page<BookAuthorDto> serachBbooks(BookSearchCondition cond, Pageable pageable){
		return bookService.searchBook(cond, pageable);
	}
}
