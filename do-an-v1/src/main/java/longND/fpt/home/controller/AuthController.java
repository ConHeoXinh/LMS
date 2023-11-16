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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import longND.fpt.home.request.ChangePasswordRequest;
import longND.fpt.home.request.ForgotPasswordRequest;
import longND.fpt.home.request.LoginRequest;
import longND.fpt.home.request.RegisterRequest;
import longND.fpt.home.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest,
			HttpServletRequest servletRequest) {
		return authService.register(registerRequest, servletRequest);
	}

	@GetMapping("/logout")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
	public ResponseEntity<?> logout(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) {
		return authService.logout(servletRequest, httpServletResponse);
	}

	@PutMapping("/change-password")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		return authService.changePassword(changePasswordRequest);
	}

	@GetMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String email, HttpServletRequest servletRequest) {
		return authService.forgotPassword(email, servletRequest);
	}

	@GetMapping("/confirm-forgot-password")
	public ResponseEntity<?> confirmForgotPassword(@RequestParam("otp") String otp) {
		return authService.confirmResetPassword(otp);
	}

	@PutMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		return authService.resetPassword(forgotPasswordRequest);
	}
}
