package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.request.EditProfileRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.JwtResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface UserService {

	public ResponseEntity<ObjectResponse> findUserById(Long userId);
	public ResponseEntity<JwtResponse> profile();
	public ResponseEntity<ApiResponse> editProfile(EditProfileRequest editProfileRequest);

	public ResponseEntity<ApiResponse> editAvatar(MultipartFile file);
	
}
