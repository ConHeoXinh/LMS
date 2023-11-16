package longND.fpt.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.service.DepartmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	@Autowired
	private DepartmentService authorService;

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertDepartment(@RequestBody CreateDPRequest pubRequest) {
		return authorService.createDepartment(pubRequest);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editDepartment(@RequestBody CreateDPRequest pubRequest,
			@RequestParam("id") Long publisherId) {
		return authorService.editDepartment(pubRequest, publisherId);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearch(@RequestParam("index-page") int indexPage) {
		return authorService.listDepartment(indexPage);
	}

	@PostMapping("/fill-search")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearchByName(@RequestBody SearchRequest nameSearch,
			@RequestParam("index-page") int indexPage) {
		return authorService.searchDepartment(nameSearch, indexPage);
	}
}
