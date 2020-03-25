package net.ecbank.fwk.admin.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;

public interface BookRepositoryCustom {

	public Page<BookAuthorDto> searchBook(BookSearchCondition cond, Pageable pageable);

}
