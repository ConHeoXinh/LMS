package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import longND.fpt.home.request.ChangePasswordRequest;
import longND.fpt.home.request.ForgotPasswordRequest;
import longND.fpt.home.request.LoginRequest;
import longND.fpt.home.request.RegisterRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface AuthService {
	public ResponseEntity<ObjectResponse> register(RegisterRequest registerRequest, HttpServletRequest servletRequest);

	public ResponseEntity<ObjectResponse> login(LoginRequest signInRequest);

	public ResponseEntity<ApiResponse> changePassword(ChangePasswordRequest changePasswordRequest);

	public ResponseEntity<ApiResponse> forgotPassword(String email, HttpServletRequest servletRequest);

	public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response);

	public ResponseEntity<ApiResponse> confirmResetPassword(String token);

	public ResponseEntity<ApiResponse> resetPassword(ForgotPasswordRequest forgotPasswordRequest);

	public ResponseEntity<ObjectResponse> existAccountByEmail(String email);

	public ResponseEntity<ObjectResponse> existAccountByUsername(String username);

}
