package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Book;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.JwtResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.BookImageService;

@Service
public class BookImageServiceImpl implements BookImageService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public ResponseEntity<ObjectResponse> getAllBookImageByBookID(Long bookID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> insertBookImage(MultipartFile multipartFile, Long bookID) {

		if (!bookRepository.existsById(bookID)) {
			throw new NotFoundException("Book_id khong ton tai");
		} else {
			if (multipartFile.isEmpty()) {
				throw new NotFoundException("File trong");
			} else {
				Book book = bookRepository.getBookById(bookID);
				String fileName = cloudinaryService.upload(multipartFile);
				book.setImageUrl(fileName);

				bookRepository.save(book);

			}
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Tao book thanh cong", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> insertBookImageOneByBookID(MultipartFile multipartFile, Long bookID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> editBookImageByBookImageID(Long bookID, MultipartFile multipartFile) {
		if (!bookRepository.existsById(bookID)) {
			throw new NotFoundException("book-id khong ton tai");
		} else {
			Book book = bookRepository.getBookById(bookID);

			String fileName = cloudinaryService.upload(multipartFile);
			book.setImageUrl(fileName);
			bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Edit image thanh cong", 200));
		}
	}

	@Override
	public ResponseEntity<JwtResponse> downloadImage(Long bookID) {
		if (!bookRepository.existsById(bookID)) {
			throw new NotFoundException("book-id khong ton tai");
		} else {

			List<byte[]> bytes = new ArrayList<>();

			Book book = bookRepository.getBookById(bookID);

			List<String> list = List.of(book.getImageUrl().split("/"));
			byte[] b = cloudinaryService.download(list.get(3));
			bytes.add(b);

			return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(HttpStatus.OK.name(), bytes));
		}
	}

}
