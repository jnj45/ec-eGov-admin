package net.ecbank.fwk.admin.sample.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import net.ecbank.fwk.admin.sample.entity.Author;
import net.ecbank.fwk.admin.sample.entity.Book;

@SpringBootTest
class BookRepositoryTest {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	@Transactional
	@Rollback(false) //메소드 완료 후 commit
	void createData() {
		Author author1 = new Author("author1");
		Author author2 = new Author("author2");
		authorRepository.save(author1);
		authorRepository.save(author2);
		
		int cnt = 100;
		for(int i=0; i < cnt; i++) {
			Book book = new Book("book"+i, i*100, i%2==0 ? author1 : author2);
			bookRepository.save(book);
		}
	}

}
