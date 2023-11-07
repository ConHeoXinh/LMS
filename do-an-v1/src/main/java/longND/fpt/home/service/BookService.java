package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.request.BookRequest;
import longND.fpt.home.request.EditBookRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface BookService {

	public ResponseEntity<ApiResponse> addBook(BookRequest bookRequest);

	public ResponseEntity<ApiResponse> editBook(EditBookRequest editBookRequest, Long bookId);

	public ResponseEntity<ObjectResponse> getAllBook();

	public ResponseEntity<ObjectResponse> getDetailBook(Long bookId);

	public ResponseEntity<ApiResponse> deleteBook(Long bookId);
}
