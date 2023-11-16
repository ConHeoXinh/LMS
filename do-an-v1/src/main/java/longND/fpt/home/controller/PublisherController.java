package longND.fpt.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.service.PublisherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/publisher")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertPublisher(@RequestBody CreateDPRequest pubRequest) {
		return publisherService.createPublisher(pubRequest);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editPublisher(@RequestBody CreateDPRequest pubRequest,
			@RequestParam("id") Long publisherId) {
		return publisherService.editPublisher(pubRequest, publisherId);
	}

//	@GetMapping("")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> getSearch() {
//		return publisherService.listAuthor(1);
//	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearch(@RequestParam("index-page") int indexPage) {
		return publisherService.listPublisher(indexPage);
	}

	@PostMapping("/fill-search")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getSearchByName(@RequestBody SearchRequest nameSearch,
			@RequestParam("index-page") int indexPage) {
		return publisherService.searchPublisher(nameSearch, indexPage);
	}

}
