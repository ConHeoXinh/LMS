package com.fpt.doan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.doan.data.dto.BookDto;
import com.fpt.doan.data.entity.Book;
import com.fpt.doan.service.BookService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<BookDto> getAllBook() {
		return bookService.getBooks();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookDto getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Book addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book updateStudent(@RequestBody BookDto bookDto, @PathVariable int id) {
		return bookService.updateBook(bookDto, id);
	}
}
