package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.AuthorDto;
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

		List<Book> bookList = bookRepository.listSearchFilter();

		List<SearchDto> vDtos = new ArrayList<>();
		for (Book book : bookList) {
			SearchDto searchDto = modelMapper.map(book, SearchDto.class);
			vDtos.add(searchDto);
		}

		List<SearchDto> list = new ArrayList<>();

		if (Objects.isNull(bookList)) {
			throw new NotFoundException("search khong co data");
		} else {

			vDtos.forEach(s -> {
				List<Department> depart = departmentRepository.findAll();
				List<DepartmentDto> departmentDtoList = new ArrayList<>();
				for (Department item : depart) {
					DepartmentDto departmentDto = modelMapper.map(item, DepartmentDto.class);
					departmentDtoList.add(departmentDto);
				}

				List<Author> authors = authorRepository.findAll();
				List<AuthorDto> authorDtoList = new ArrayList<>();
				for (Author item : authors) {
					AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
					authorDtoList.add(authorDto);
				}

				List<Publisher> publisher = publisherRepository.findAll();
				List<PublisherDto> publisherDtoList = new ArrayList<>();
				for (Publisher item : publisher) {
					PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
					publisherDtoList.add(publisherDto);
				}

				SearchDto dto = SearchDto.builder().bookId(s.getBookId()).title(s.getTitle())
						.desciption(s.getDesciption()).price(s.getPrice()).imageUrl(s.getImageUrl())
						.copies(s.getCopies()).copies_available(s.getCopies_available())
						.departmentDtoList(departmentDtoList).authorDtoList(authorDtoList)
						.publisheDtoList(publisherDtoList).build();

				list.add(dto);
			});

			List<SearchDto> resultSearch = list;

			List<SearchDto> sort = new ArrayList<>();

			if (searchFilterRequest.getStatusSortPrice() == 1) {
				System.out.println(searchFilterRequest.getStatusSortPrice());
				sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice))
						.collect(Collectors.toList());
			} else if (searchFilterRequest.getStatusSortPrice() == 2) {
				System.out.println(searchFilterRequest.getStatusSortPrice());
				sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice).reversed())
						.collect(Collectors.toList());
			} else {
				sort = resultSearch;
			}

			List<SearchDto> price = new ArrayList<>();

			if (searchFilterRequest.getMinPrice() == 0 || searchFilterRequest.getMaxPrice() == 0) {
				price = sort;
			} else {
				price = sort.stream()
						.filter(searchDto -> searchDto.getPrice() >= searchFilterRequest.getMinPrice()
								&& searchDto.getPrice() <= searchFilterRequest.getMaxPrice())
						.collect(Collectors.toList());
			}

			if (price.isEmpty()) {
				throw new NotFoundException("khong co data search");
			} else {

				int totalSearch = price.size();

				int totalPage = (totalSearch % 16 == 0) ? totalSearch / 16 : totalSearch / 16 + 1;

				List<SearchDto> finalSort = price.stream().skip(page).limit(10).collect(Collectors.toList());

				List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();

				finalSort.forEach(v -> {
					ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getBookId()).title(v.getTitle())
							.description(v.getDesciption()).price(v.getPrice()).imageUrl(v.getImageUrl()).build();
					viewSearchDtoList.add(dto);
				});
				return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
					{
						put("searchList", viewSearchDtoList);
						put("sizePage", totalPage);
					}
				}));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getTextSearch(SearchRequest searchRequest) {
		List<Book> bookList = bookRepository.searchBookByTitle(searchRequest.getSearchText(), PageRequest.of(0, 5));

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

}
