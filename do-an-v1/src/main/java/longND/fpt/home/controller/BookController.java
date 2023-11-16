package longND.fpt.home.controller;

import java.util.Iterator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
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

	@PostMapping(value = "/insert", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> insertBook(@RequestPart("book") String book, @RequestPart("imageUrl") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();

		BookRequest bookRequest = new BookRequest();
		try {
			bookRequest = objectMapper.readValue(book, BookRequest.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookService.addBook(bookRequest, file);
	}
//	public ResponseEntity<?> insertBook(@Valid @RequestBody BookRequest bookRequest, @RequestParam("imageUrl") MultipartFile file) {
//		return bookService.addBook(bookRequest);
//	}

	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> editBook(@RequestPart("book") String book, @RequestParam("id") Long bookId,
			@RequestParam("imageUrl") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();

		EditBookRequest editBookRequest = new EditBookRequest();
		try {
			editBookRequest = objectMapper.readValue(book, EditBookRequest.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookService.editBook(editBookRequest, bookId, file);
	}

	@GetMapping("")
	public ResponseEntity<?> getBookForHome() {
		return bookService.getBookForHome();
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

	@PostMapping("/remove-favorite")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> removeFavoritesBook(@RequestParam("book-id") Long bookId) {
		return favoriteSevice.editLikeBookByUser(bookId);
	}

//	@PostMapping(value = "/insert-book-image")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> insertPostImage(@RequestParam("file") MultipartFile multipartFiles,
//			@RequestParam("book-id") Long bookID) {
//		return bookImageService.insertBookImage(multipartFiles, bookID);
//	}

//	@GetMapping("/download-image")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> downloadImage(@RequestParam("book-id") Long bookID) {
//		return bookImageService.downloadImage(bookID);
//	}
//
//	@PutMapping("/edit-image")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> editImage(@RequestParam("book-id") Long bookID,
//			@RequestParam("file") MultipartFile multipartFiles) {
//		return bookImageService.editBookImageByBookImageID(bookID, multipartFiles);
//	}
}
