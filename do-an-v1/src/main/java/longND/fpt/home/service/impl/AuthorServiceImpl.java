package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.AuthorDto;
import longND.fpt.home.dto.CustomPage;
import longND.fpt.home.dto.DepartmentDto;
import longND.fpt.home.dto.PublisherDto;
import longND.fpt.home.dto.SearchDto;
import longND.fpt.home.dto.ViewSearchDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Author;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Department;
import longND.fpt.home.repository.AuthorRepository;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.request.CreateAuthorRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public ResponseEntity<ApiResponse> createAuthor(CreateAuthorRequest creatRequest) {
		String nameAuthor = creatRequest.getName();
		if (authorRepository.existsByName(nameAuthor)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên Author đã tồn tại");
		} else {
			Author author = new Author();
			author.setName(nameAuthor);
			author.setImageUrl(creatRequest.getImageUrl());
			author.setDescription(creatRequest.getDescription());
			authorRepository.save(author);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add Author success", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editAuthor(CreateAuthorRequest creatRequest, Long AuthorId) {
		Author author = authorRepository.getById(AuthorId);
		if (Objects.isNull(author)) {
			throw new NotFoundException("Can't find Author");
		} else {
			String nameAuthor = creatRequest.getName();
			if (authorRepository.existsByName(nameAuthor)) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên Author đã tồn tại");
			} else {
				author.setName(nameAuthor);
				author.setImageUrl(creatRequest.getImageUrl());
				author.setDescription(creatRequest.getDescription());
				authorRepository.save(author);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update Author success", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchAuthor(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Author> listAuthor = authorRepository.listAuthor(text, pageable);

		List<AuthorDto> list = new ArrayList<>();

		for (Author item : listAuthor.getContent()) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			list.add(authorDto);
		}

		CustomPage<AuthorDto> pageResponse = new CustomPage<>(list, indexPage, size, listAuthor.getTotalElements(),
				listAuthor.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listAuthor(int indexPage) {
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Author> listAuthor = authorRepository.getListAuthor(pageable);
		List<AuthorDto> list = new ArrayList<>();

		for (Author item : listAuthor.getContent()) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			list.add(authorDto);
		}

		CustomPage<AuthorDto> pageResponse = new CustomPage<>(list, indexPage, size, listAuthor.getTotalElements(),
				listAuthor.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("list Author", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getBookOfAuthor(Long authorId, int indexPage) {
		int size = 2;
		int page = indexPage - 1;
		Pageable pageable = PageRequest.of(page, size);

		Author author = authorRepository.getById(authorId);
		if (Objects.isNull(author)) {
			throw new NotFoundException("Author not found");
		} else {
			Page<Book> listBook = bookRepository.listBookByAuthorId(authorId, pageable);
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);

			List<SearchDto> list = new ArrayList<>();

			for (Book book : listBook.getContent()) {
				SearchDto searchDto = convertToSearchDto(book);
				list.add(searchDto);
			}

			List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();

			list.forEach(v -> {
				ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getBookId()).title(v.getTitle())
						.description(v.getDesciption()).price(v.getPrice()).imageUrl(v.getImageUrl()).build();
				viewSearchDtoList.add(dto);
			});

			CustomPage<ViewSearchDto> pageResponse = new CustomPage<>(viewSearchDtoList, indexPage, size,
					listBook.getTotalElements(), listBook.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
				{
					put("authorDetail", authorDto);
					put("bookList", pageResponse);
				}
			}));
		}
	}

	private SearchDto convertToSearchDto(Book book) {
		SearchDto searchDto = new SearchDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		searchDto.setAuthorDtoList(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		searchDto.setDepartmentDtoList(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		searchDto.setPublisheDto(publisherDto);

		searchDto.setBookId(book.getId());
		searchDto.setTitle(book.getTitle());
		searchDto.setDesciption(book.getDescription());
		searchDto.setPrice(book.getPrice());
		searchDto.setImageUrl(book.getImageUrl());
		searchDto.setCopies(book.getCopies());
		searchDto.setCopies_available(book.getCopies_available());
		searchDto.setLanguage(book.getLanguage());

		return searchDto;
	}

}
