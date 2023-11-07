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

import jakarta.validation.Valid;
import longND.fpt.home.request.BookRequest;
import longND.fpt.home.request.EditBookRequest;
import longND.fpt.home.service.BookService;
import longND.fpt.home.service.FavoriteSevice;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private FavoriteSevice favoriteSevice;

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertBook(@Valid @RequestBody BookRequest bookRequest) {
		return bookService.addBook(bookRequest);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editBook(@RequestBody EditBookRequest editBookRequest, @RequestParam("id") Long bookId) {
		return bookService.editBook(editBookRequest, bookId);
	}

	@GetMapping("")
	public ResponseEntity<?> getAllBooks() {
		return bookService.getAllBook();
	}

	@GetMapping("/get-one")
	public ResponseEntity<?> getDetailBook(@RequestParam("id") Long id) {
		return bookService.getDetailBook(id);
	}

	@PutMapping("/delete-book")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> deleteBook(@RequestParam("book-id") Long bookId) {
		return bookService.deleteBook(bookId);
	}

	@GetMapping("/favorite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getFavoritesBook() {
		return favoriteSevice.getAllBookFavoriteByUserId();
	}

	@PostMapping("/add-favorite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> addFavoritesBook(@RequestParam("book-id") Long bookId) {
		return favoriteSevice.createLikeBookByUser(bookId);
	}
}
