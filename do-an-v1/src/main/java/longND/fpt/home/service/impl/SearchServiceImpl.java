package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Author;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Department;
import longND.fpt.home.modal.Publisher;
import longND.fpt.home.repository.AuthorRepository;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.repository.DepartmentRepository;
import longND.fpt.home.repository.PublisherRepository;
import longND.fpt.home.request.SearchFilterRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

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
	public ResponseEntity<ObjectResponse> searchByTitle(SearchRequest searchRequest, int indexPage) {
//		int size = 16;
//        int page = indexPage - 1;
//
//        if (Objects.isNull(searchRequest)) {
//            throw new NotFoundException("Search null");
//        } else {
//
//            Page<SearchDto> searchDtoListByTitle = bookRepository.listSearchPostByTitle(searchRequest.getSearchText(), PageRequest.of(page, size));
//
//            List<SearchDto> list = new ArrayList<>();
//
//            if (Objects.isNull(searchDtoListByTitle)) {
//                throw new NotFoundException("search khong co data");
//            } else {
//                searchDtoListByTitle.forEach(s -> {
//                    SearchDto dto = SearchDto.builder()
//                            .postID(s.getPostID())
//                            .title(s.getTitle())
//                            .address(s.getAddress())
//                            .imageUrl(s.getImageUrl())
//                            .price(s.getPrice())
//                            .fullName(s.getFullName())
//                            .nameVoucher(s.getNameVoucher())
//                            .typeAccount(s.getTypeAccount())
//                            .avgStar(s.getAvgStar())
//                            .build();
//                    list.add(dto);
//                });
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("search success", new HashMap<>() {
//                {
//                    put("searchList", list);
//                    put("sizePage", searchDtoListByTitle.getTotalPages());
//                }
//            }));
//        }

		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> searchFilter(SearchFilterRequest searchFilterRequest, int indexPage) {
		int size = 16;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Book> listBook = bookRepository.listSearchFilter(searchFilterRequest.getAuthor(),
				searchFilterRequest.getDepartment(), searchFilterRequest.getPublisher(), searchFilterRequest.getTitle(),
				pageable);

		List<SearchDto> list = new ArrayList<>();

		for (Book book : listBook.getContent()) {
			SearchDto searchDto = convertToSearchDto(book);
			list.add(searchDto);
		}

		List<SearchDto> resultSearch = list;

		List<SearchDto> sort = new ArrayList<>();

		if (searchFilterRequest.getStatusSortPrice() == 1) {
			System.out.println(searchFilterRequest.getStatusSortPrice());
			sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice)).toList();
		} else if (searchFilterRequest.getStatusSortPrice() == 2) {
			System.out.println(searchFilterRequest.getStatusSortPrice());
			sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice).reversed()).toList();
		} else {
			sort = resultSearch;
		}

		List<SearchDto> price = new ArrayList<>();

		if (searchFilterRequest.getMinPrice() == 0 || searchFilterRequest.getMaxPrice() == 0) {
			price = sort;
		} else {
			price = sort.stream().filter(searchDto -> searchDto.getPrice() >= searchFilterRequest.getMinPrice()
					&& searchDto.getPrice() <= searchFilterRequest.getMaxPrice()).toList();
//					.collect(Collectors.toList());
		}

		if (price.isEmpty()) {
			throw new NotFoundException("khong co data search");
		} else {

			List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();

			price.forEach(v -> {
				ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getBookId()).title(v.getTitle())
						.description(v.getDesciption()).price(v.getPrice()).imageUrl(v.getImageUrl())
						.copies(v.getCopies()).copies_available(v.getCopies_available()).build();
				viewSearchDtoList.add(dto);
			});

			CustomPage<ViewSearchDto> pageResponse = new CustomPage<>(viewSearchDtoList, indexPage, size,
					listBook.getTotalElements(), listBook.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
				{
					put("searchList", pageResponse);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getTextSearch(SearchRequest searchRequest) {
		List<Book> bookList = bookRepository.listSearchBookByTitle(searchRequest.getSearchText());

		List<ViewSearchDto> vDtos = new ArrayList<>();
		for (Book book : bookList) {
			ViewSearchDto viewSearchDto = modelMapper.map(book, ViewSearchDto.class);
			vDtos.add(viewSearchDto);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
			{
				put("listBook", vDtos);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getFilterMenu() {
		List<Author> authors = authorRepository.findAll();
		List<Department> departments = departmentRepository.findAll();
		List<Publisher> publishers = publisherRepository.findAll();

		List<DepartmentDto> departmentDtoList = new ArrayList<>();
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<PublisherDto> publisherDtoList = new ArrayList<>();

		for (Department item : departments) {
			DepartmentDto departmentDto = modelMapper.map(item, DepartmentDto.class);
			departmentDtoList.add(departmentDto);
		}

		for (Author item : authors) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			authorDtoList.add(authorDto);
		}

		for (Publisher item : publishers) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			publisherDtoList.add(publisherDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search menu", new HashMap<>() {
			{
				put("listAuthor", authorDtoList);
				put("listDepartment", departmentDtoList);
				put("listPublisher", publisherDtoList);
			}
		}));
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
