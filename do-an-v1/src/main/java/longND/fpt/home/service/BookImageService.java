package longND.fpt.home.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.JwtResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface BookImageService {

	public ResponseEntity<ObjectResponse> getAllBookImageByBookID(Long bookID);

	public ResponseEntity<ApiResponse> insertBookImage(MultipartFile multipartFile, Long bookID);

	public ResponseEntity<ApiResponse> insertBookImageOneByBookID(MultipartFile multipartFile, Long bookID);

	public ResponseEntity<ApiResponse> editBookImageByBookImageID(Long bookID, MultipartFile multipartFile);

	public ResponseEntity<JwtResponse> downloadImage(Long bookID);
}
