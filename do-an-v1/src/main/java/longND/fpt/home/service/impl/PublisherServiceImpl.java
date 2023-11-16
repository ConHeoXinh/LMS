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

import longND.fpt.home.dto.CustomPage;
import longND.fpt.home.dto.PublisherDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Publisher;
import longND.fpt.home.repository.PublisherRepository;
import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> createPublisher(CreateDPRequest creatRequest) {
		String namePublisher = creatRequest.getName();
		if (publisherRepository.existsByName(namePublisher)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên publisher đã tồn tại");
		} else {
			Publisher publisher = new Publisher();
			publisher.setName(namePublisher);
			publisherRepository.save(publisher);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add publisher success", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editPublisher(CreateDPRequest creatRequest, Long publisherId) {
		Publisher publisher = publisherRepository.getById(publisherId);
		if (Objects.isNull(publisher)) {
			throw new NotFoundException("Can't find publisher");
		} else {
			String namePublisher = creatRequest.getName();
			if (publisherRepository.existsByName(namePublisher)) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên publisher đã tồn tại");
			} else {
				publisher.setName(namePublisher);
				publisherRepository.save(publisher);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update publisher success", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchPublisher(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Publisher> listPublisher = publisherRepository.listPublisher(text, pageable);

		List<PublisherDto> list = new ArrayList<>();

		for (Publisher item : listPublisher.getContent()) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			list.add(publisherDto);
		}

		CustomPage<PublisherDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listPublisher.getTotalElements(), listPublisher.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listPublisher(int indexPage) {
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Publisher> listPublisher = publisherRepository.getListPublisher(pageable);
		List<PublisherDto> list = new ArrayList<>();

		for (Publisher item : listPublisher.getContent()) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			list.add(publisherDto);
		}

		CustomPage<PublisherDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listPublisher.getTotalElements(), listPublisher.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("list publisher", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

}
