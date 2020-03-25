package net.ecbank.fwk.admin.sample.repository;

import static net.ecbank.fwk.admin.sample.entity.QAuthor.author;
import static net.ecbank.fwk.admin.sample.entity.QBook.book;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.sample.dto.BookAuthorDto;
import net.ecbank.fwk.admin.sample.dto.BookSearchCondition;
import net.ecbank.fwk.admin.sample.dto.QBookAuthorDto;

@RequiredArgsConstructor //생성자로 em, queryFacotry 주입
public class BookRepositoryImpl implements BookRepositoryCustom {
	
	private final EntityManager em;
	private final JPAQueryFactory queryFactory; //@Bean으로 등록했음.
	
	@Override
	public Page<BookAuthorDto> searchBook(BookSearchCondition cond, Pageable pageable){
		QueryResults<BookAuthorDto> results = queryFactory
			.select(new QBookAuthorDto(
						book.id.as("bookId"),
						book.title.as("bookTitle"),
						book.totalPage,
						author.id.as("authorId"),
						author.name.as("authorName")
					))
			.from(book)
			.where(
					bookTitleLike(cond.getBookTitle()),
					authorNameLike(cond.getAuthorName()),
					totalPageLoe(cond.getTotalPageLoe()),
					totalPageGoe(cond.getTotalPageGoe())
				  )
			.leftJoin(book.author, author)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();
		
		List<BookAuthorDto> list = results.getResults();
		long totalCnt = results.getTotal();
						
		return new PageImpl<>(list, pageable, totalCnt);
	}
	
	private BooleanExpression bookTitleLike(String bookTitle) {
		return hasText(bookTitle) ? book.title.contains(bookTitle) : null;
	}
	
	private BooleanExpression authorNameLike(String authorName) {
		return hasText(authorName) ? author.name.contains(authorName) : null;
	}
	
	private BooleanExpression totalPageLoe(Long totalPage) {
		return totalPage !=null ? book.totalPage.loe(totalPage) : null;
	}
	
	private BooleanExpression totalPageGoe(Long totalPage) {
		return totalPage != null ? book.totalPage.goe(totalPage) : null;
	}
}
