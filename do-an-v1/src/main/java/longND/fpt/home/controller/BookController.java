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
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.request.BookRequest;
import longND.fpt.home.request.EditBookRequest;
import longND.fpt.home.service.BookImageService;
import longND.fpt.home.service.BookService;
import longND.fpt.home.service.FavoriteSevice;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookImageService bookImageService;

	@Autowired
	private FavoriteSevice favoriteSevice;

	@PostMapping(value = "/insert")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertBook(@RequestBody BookRequest bookRequest) {
		return bookService.addBook(bookRequest);
	}

	@PutMapping(value = "/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editBook(@RequestBody EditBookRequest editBookRequest, @RequestParam("id") Long bookId) {
		return bookService.editBook(editBookRequest, bookId);
	}

	@GetMapping("")
	public ResponseEntity<?> getBookForHome() {
		return bookService.getBookForHome();
	}

	@GetMapping("/books")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getAllBook(@RequestParam("index-page") int indexPage) {
		return bookService.getAllBook(indexPage);
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
	public ResponseEntity<?> getFavoritesBook(@RequestParam("index-page") int indexPage) {
		return favoriteSevice.getAllBookFavoriteByUserId(indexPage);
	}

	@PostMapping("/add-favorite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> addFavoritesBook(@RequestParam("book-id") Long bookId) {
		return favoriteSevice.createLikeBookByUser(bookId);
	}

	@PostMapping("/remove-favorite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> removeFavoritesBook(@RequestParam("book-id") Long bookId) {
		return favoriteSevice.editLikeBookByUser(bookId);
	}

	@PostMapping(value = "/insert-book-image")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertPostImage(@RequestParam("file") MultipartFile multipartFiles) {
		return bookImageService.insertBookImage(multipartFiles);
	}

//	@GetMapping("/download-image")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> downloadImage(@RequestParam("book-id") Long bookID) {
//		return bookImageService.downloadImage(bookID);
//	}

	@PutMapping("/edit-image")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editImage(@RequestParam("book-id") Long bookID,
			@RequestParam("file") MultipartFile multipartFiles) {
		return bookImageService.editBookImageByBookImageID(bookID, multipartFiles);
	}
}
