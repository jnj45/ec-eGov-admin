package net.ecbank.fwk.admin.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.entity.Book;

public interface BookRepositoryCustom {

	Page<Book> searchBooks(BookSearchCondition cond, Pageable pageable);

	Page<BookAuthorDto> searchBooksDto(BookSearchCondition cond, Pageable pageable);

}
