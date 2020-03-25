package net.ecbank.fwk.admin.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sample.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

}
