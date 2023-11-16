package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.request.CreateAuthorRequest;
import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface AuthorService {

	public ResponseEntity<ApiResponse> createAuthor(CreateAuthorRequest creatRequest);

	public ResponseEntity<ApiResponse> editAuthor(CreateAuthorRequest creatRequest, Long publisherId);

	public ResponseEntity<ObjectResponse> searchAuthor(SearchRequest searchRequest, int indexPage);

	public ResponseEntity<ObjectResponse> listAuthor(int indexPage);

	public ResponseEntity<ObjectResponse> getBookOfAuthor(Long authorId, int indexPage);
}
