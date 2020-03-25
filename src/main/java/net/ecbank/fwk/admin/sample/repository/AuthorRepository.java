package net.ecbank.fwk.admin.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sample.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
