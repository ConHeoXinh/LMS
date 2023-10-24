package com.fpt.doan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.doan.data.dto.BookDto;
import com.fpt.doan.data.entity.Book;

@Service
public interface BookService {

	List<BookDto> getBooks();

	BookDto getBookById(Integer id);
	
	void deleteBook(Integer id);
	
	Book updateBook(BookDto bookDto, Integer id);
	
	Book addBook(BookDto bookDto);
}
