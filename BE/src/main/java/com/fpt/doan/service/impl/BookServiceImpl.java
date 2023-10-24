package com.fpt.doan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.doan.data.dto.BookDto;
import com.fpt.doan.data.entity.Book;
import com.fpt.doan.data.repository.BookRepository;
import com.fpt.doan.exception.EmptyDataException;
import com.fpt.doan.exception.NotFoundException;
import com.fpt.doan.exception.SaveDataException;
import com.fpt.doan.exception.UpdateDataException;
import com.fpt.doan.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<BookDto> getBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = new ArrayList<>();

		for (Book book : books) {
			BookDto bookDto = modelMapper.map(book, BookDto.class);
			bookDtos.add(bookDto);
		}

		return bookDtos;
	}

	@Override
	public BookDto getBookById(Integer id) {
		BookDto bookDto = new BookDto();
		try {
			Book book = bookRepository.findById(id).orElseThrow(() -> new EmptyDataException("Ko tim thay sach"));
			bookDto = modelMapper.map(book, BookDto.class);
		} catch (NotFoundException e) {
			throw new NotFoundException("Book is null");
		}
		return bookDto;
	}

	@Override
	public void deleteBook(Integer id) {
		if (!bookRepository.existsById(id)) {
			throw new NotFoundException("Not found a book");
		}
		bookRepository.deleteById(id);
	}

	@Override
	public Book updateBook(BookDto bookDto, Integer id) {
		try {
			if (!bookRepository.existsById(id)) {
				throw new NotFoundException("Sorry, this book could not be found");
			}

			Book book = bookRepository.findById(id).get();
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			book.setDepartment(bookDto.getDepartment());
			book.setDescription(bookDto.getDescription());
			book.setImg(bookDto.getImg());
			book.setTitle(bookDto.getTitle());
			book.setCopies(bookDto.getCopies());
			book.setCopies_available(bookDto.getCopies_available());
			book.setLanguage(bookDto.getLanguage());
			book.setDate_import(bookDto.getDate_import());

			bookRepository.save(book);
		} catch (SaveDataException e) {
			throw new SaveDataException("Error save data");
		}

		return null;
	}

	@Override
	public Book addBook(BookDto bookDto) {
		try {
			if (bookAlreadyExists(bookDto.getName())) {
				throw new SaveDataException(bookDto.getName() + " already exists!");
			}
			Book book = new Book();
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			book.setDepartment(bookDto.getDepartment());
			book.setDescription(bookDto.getDescription());
			book.setImg(bookDto.getImg());
			book.setTitle(bookDto.getTitle());
			book.setCopies(0);
			book.setCopies_available(bookDto.getCopies_available());
			book.setLanguage(bookDto.getLanguage());

			Date date = new Date();

			book.setDate_import(date);
			bookRepository.save(book);
		} catch (UpdateDataException e) {
			throw new UpdateDataException("Error update data");
		}

		return null;
	}

	private boolean bookAlreadyExists(String name) {
		return bookRepository.findByName(name).isPresent();
	}
}
