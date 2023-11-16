package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.request.CreateDPRequest;
import longND.fpt.home.request.SearchRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface PublisherService {

	public ResponseEntity<ApiResponse> createPublisher(CreateDPRequest creatRequest);

	public ResponseEntity<ApiResponse> editPublisher(CreateDPRequest creatRequest, Long publisherId);

	public ResponseEntity<ObjectResponse> searchPublisher(SearchRequest searchRequest, int indexPage);

	public ResponseEntity<ObjectResponse> listPublisher(int indexPage);
}
