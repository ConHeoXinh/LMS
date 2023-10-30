package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.BookDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Author;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Department;
import longND.fpt.home.modal.Publisher;
import longND.fpt.home.modal.User;
import longND.fpt.home.repository.AuthorRepository;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.repository.DepartmentRepository;
import longND.fpt.home.repository.PublisherRepository;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.request.BookRequest;
import longND.fpt.home.request.EditBookRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.BookService;
import longND.fpt.home.util.SecurityUtils;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> addBook(BookRequest bookRequest) {

		if (bookRepository.existsByTitle(bookRequest.getTitle())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên sách đã tồn tại");
		} else {
			Book book = new Book();
			book.setTitle(bookRequest.getTitle());
			book.setDescription(bookRequest.getDescription());
			book.setPrice(bookRequest.getPrice());
			book.setImageUrl(bookRequest.getImageUrl());
			book.setCreateAt(LocalDateTime.now());
			book.setCopies(0);
			book.setCopies_available(bookRequest.getCopies_available());
			book.setLanguage(bookRequest.getLanguage());
			book.setForUser(bookRequest.isForUser());
			book.setPage(bookRequest.getPage());

			List<Author> authors = new ArrayList<>();
			if (!Objects.isNull(bookRequest.getAuthors())) {
				bookRequest.getAuthors().forEach(a -> {

					Author author = authorRepository.getById(a);
					if (!Objects.isNull(author)) {
						authors.add(author);
					}
				});
				book.setAuthors(authors);
			}

			List<Department> departments = new ArrayList<>();
			if (!Objects.isNull(bookRequest.getDepartments())) {
				bookRequest.getDepartments().forEach(d -> {

					Department department = departmentRepository.getById(d);
					if (!Objects.isNull(department)) {
						departments.add(department);
					}
				});
				book.setDepartments(departments);
				;
			}
			if (!Objects.isNull(bookRequest.getDepartments())) {
				Publisher publisher = publisherRepository.getById(bookRequest.getPublisher());

				book.setPublisher(publisher);
			}
			bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add book success", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editBook(EditBookRequest editBookRequest, Long bookId) {
		if (Objects.isNull(bookId)) {
			throw new NotFoundException("Book_id null");
		} else {
			Book book = bookRepository.getBookById(bookId);

			if (Objects.isNull(book)) {
				throw new NotFoundException("Book null");
			} else {

				book.setTitle(editBookRequest.getTitle());
				book.setDescription(editBookRequest.getDescription());
				book.setPrice(editBookRequest.getPrice());
				book.setImageUrl(editBookRequest.getImageUrl());
				book.setCreateAt(LocalDateTime.now());
				book.setCopies(editBookRequest.getCopies());
				book.setCopies_available(editBookRequest.getCopies_available());
				book.setLanguage(editBookRequest.getLanguage());
				book.setForUser(editBookRequest.isForUser());
				book.setPage(editBookRequest.getPage());

				List<Author> authors = new ArrayList<>();
				if (!Objects.isNull(editBookRequest.getAuthors())) {
					editBookRequest.getAuthors().forEach(a -> {

						Author author = authorRepository.getById(a);
						if (!Objects.isNull(author)) {
							authors.add(author);
						}
					});
					book.setAuthors(authors);
				}

				List<Department> departments = new ArrayList<>();
				if (!Objects.isNull(editBookRequest.getDepartments())) {
					editBookRequest.getDepartments().forEach(d -> {

						Department department = departmentRepository.getById(d);
						if (!Objects.isNull(department)) {
							departments.add(department);
						}
					});
					book.setDepartments(departments);
					;
				}
				if (!Objects.isNull(editBookRequest.getDepartments())) {
					Publisher publisher = publisherRepository.getById(editBookRequest.getPublisher());

					book.setPublisher(publisher);
				}
				bookRepository.save(book);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update book success", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllBook() {

		List<BookDto> bookDtos = new ArrayList<>();

		List<Book> books = bookRepository.findAll();

		for (Book book : books) {
			BookDto bookDto = modelMapper.map(book, BookDto.class);
			bookDtos.add(bookDto);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("List Books", new HashMap<>() {
			{
				put("books", bookDtos);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getDetailBook(Long bookId) {
		if (Objects.isNull(bookId)) {
			throw new NotFoundException("Book_id null");
		} else {
			Book book = bookRepository.getBookById(bookId);
			BookDto bookDto = modelMapper.map(book, BookDto.class);

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Book", new HashMap<>() {
				{
					put("book", book);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchBook(String title) {
		List<Book> books = bookRepository.searchBook(title);
		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("List Books search", new HashMap<>() {
			{
				put("books", books);
			}
		}));
	}

	@Override
	public ResponseEntity<ApiResponse> deleteBook(Long bookId) {

		Book book = bookRepository.getBookById(bookId);

		if (Objects.isNull(book)) {
			throw new NotFoundException("Book_id khong ton tai trong book");
		} else {

			bookRepository.delete(book);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("delete book thanh cong", 200));

		}
	}

}
