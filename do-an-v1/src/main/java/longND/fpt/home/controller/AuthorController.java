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

import longND.fpt.home.request.CreateAuthorRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.service.AuthorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertAuthor(@RequestBody CreateAuthorRequest pubRequest) {
		return authorService.createAuthor(pubRequest);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editAuthor(@RequestBody CreateAuthorRequest pubRequest,
			@RequestParam("id") Long publisherId) {
		return authorService.editAuthor(pubRequest, publisherId);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearch(@RequestParam("index-page") int indexPage) {
		return authorService.listAuthor(indexPage);
	}

	@PostMapping("/fill-search")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearchByName(@RequestBody SearchRequest nameSearch,
			@RequestParam("index-page") int indexPage) {
		return authorService.searchAuthor(nameSearch, indexPage);
	}

	@PostMapping("/detail")
	public ResponseEntity<?> getDetailAuthor(@RequestParam("id") Long id, @RequestParam("index-page") int indexPage) {
		return authorService.getBookOfAuthor(id, indexPage);
	}
}
