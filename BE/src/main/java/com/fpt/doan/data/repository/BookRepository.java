package com.fpt.doan.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.doan.data.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value = "SELECT * FROM book WHERE book.id = :id", nativeQuery = true)
	Book getBookById(Integer id);

	Optional<Book> findByName(String name);
}
