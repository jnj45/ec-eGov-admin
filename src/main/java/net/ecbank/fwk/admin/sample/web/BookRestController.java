package net.ecbank.fwk.admin.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.intface.service.TotalLogApiService;
import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.entity.Book;
import net.ecbank.fwk.admin.sample.service.BookService;

@RestController
@RequestMapping("/sample")
public class BookRestController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	TotalLogApiService totalLogApiService;
	
	@RequestMapping("/booksDto")
	public Page<BookAuthorDto> searchBooksDto(BookSearchCondition cond, Pageable pageable){
		return bookService.searchBooksDto(cond, pageable);
	}
	
	/**
	 * entity를 json으로 변환할 때 무한루프에 빠짐. (entity간 참조가 A->B->C->A 식으로 이루어질 때)
	 * @JsonBackReference @JsonManagedReference 를 사용하면 되지만, Fetch Lazy 필드도 조회함, 
	 * Fetch Lazy 필드도 조회문제도 @JsonIgnore 사용하면 되지만. 결국 entity에 어노테이션이 많이 붙고 관리하기 까다로움
	 * -> 그냥 목록조회 같은 데이타는 dto로 변환해서 리턴하는게 좋음.
	 * @param cond
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/booksEntity")
	public Page<Book> searchBooksEntity(BookSearchCondition cond, Pageable pageable){
		Page<Book> result = bookService.searchBooks(cond, pageable);
		return result;
	}
	
	
}
