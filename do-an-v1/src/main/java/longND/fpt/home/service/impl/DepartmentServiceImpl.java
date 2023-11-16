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
import longND.fpt.home.dto.DepartmentDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Department;
import longND.fpt.home.repository.DepartmentRepository;
import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> createDepartment(CreateDPRequest creatRequest) {
		String nameDepartment = creatRequest.getName();
		if (departmentRepository.existsByName(nameDepartment)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên Department đã tồn tại");
		} else {
			Department Department = new Department();
			Department.setName(nameDepartment);
			departmentRepository.save(Department);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add Department success", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editDepartment(CreateDPRequest creatRequest, Long DepartmentId) {
		Department Department = departmentRepository.getById(DepartmentId);
		if (Objects.isNull(Department)) {
			throw new NotFoundException("Can't find Department");
		} else {
			String nameDepartment = creatRequest.getName();
			if (departmentRepository.existsByName(nameDepartment)) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên Department đã tồn tại");
			} else {
				Department.setName(nameDepartment);
				departmentRepository.save(Department);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update Department success", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchDepartment(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Department> listDepartment = departmentRepository.listDepartment(text, pageable);

		List<DepartmentDto> list = new ArrayList<>();

		for (Department item : listDepartment.getContent()) {
			DepartmentDto DepartmentDto = modelMapper.map(item, DepartmentDto.class);
			list.add(DepartmentDto);
		}

		CustomPage<DepartmentDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listDepartment.getTotalElements(), listDepartment.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("search success", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listDepartment(int indexPage) {
		int size = 2;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Department> listDepartment = departmentRepository.getListDepartment(pageable);
		List<DepartmentDto> list = new ArrayList<>();

		for (Department item : listDepartment.getContent()) {
			DepartmentDto DepartmentDto = modelMapper.map(item, DepartmentDto.class);
			list.add(DepartmentDto);
		}

		CustomPage<DepartmentDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listDepartment.getTotalElements(), listDepartment.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("list Department", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

}
