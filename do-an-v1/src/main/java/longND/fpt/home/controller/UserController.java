package longND.fpt.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.request.EditProfileRequest;
import longND.fpt.home.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
	public ResponseEntity<?> profile() {
		return userService.profile();
	}
	
	@PutMapping("/edit-avatar")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
    public ResponseEntity<?> editAvatar(@RequestParam("file") MultipartFile file) {
        return userService.editAvatar(file);
    }

	@PutMapping("/edit-profile")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
	public ResponseEntity<?> editProfile(@RequestBody EditProfileRequest editProfileRequest) {
		return userService.editProfile(editProfileRequest);
	}
}
