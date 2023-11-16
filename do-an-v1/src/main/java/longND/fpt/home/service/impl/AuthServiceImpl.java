package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.jsonwebtoken.JwtParserBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import longND.fpt.home.dto.LoginDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Role;
import longND.fpt.home.modal.User;
import longND.fpt.home.repository.RoleRepository;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.request.ChangePasswordRequest;
import longND.fpt.home.request.ForgotPasswordRequest;
import longND.fpt.home.request.LoginRequest;
import longND.fpt.home.request.RegisterRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.security.jwt.JwtTokenProvider;
import longND.fpt.home.security.jwt.UserDetailsImpl;
import longND.fpt.home.service.AuthService;
import longND.fpt.home.util.JavaMail;
import longND.fpt.home.util.SecurityUtils;

@Service
public class AuthServiceImpl implements AuthService {
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMail javaMail;

	@Override
	public ResponseEntity<ObjectResponse> register(RegisterRequest registerRequest, HttpServletRequest servletRequest) {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên tài khoàn đã tồn tại");
		} else if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
		} else {
			User user = new User(registerRequest.getEmail(), registerRequest.getPhoneNumber(),
					registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));

			user.setFirstName("first name");
			user.setLastName("last name");
			user.setDob(LocalDateTime.now());
			user.setGender(true);
			user.setCreateAt(LocalDateTime.now());

			String strRoles = registerRequest.getRole();

			if (strRoles == null || strRoles.isEmpty()) {
				Role userRole = roleRepository.findByName("ROLE_USER")
						.orElseThrow(() -> new NotFoundException("User role is not found"));
				user.setRoles(userRole);
				user.setUserStatus(true);
			} else {
				switch (strRoles) {
				case "admin": {
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new NotFoundException("Admin role is not found"));
					user.setRoles(adminRole);
					user.setUserStatus(true);
					break;
				}
				case "employee": {
					Role emplRole = roleRepository.findByName("ROLE_EMPLOYEE")
							.orElseThrow(() -> new NotFoundException("EMPLOYEE role is not found"));
					user.setRoles(emplRole);
					user.setUserStatus(true);
					break;
				}
				default:
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new NotFoundException("User role is not found"));
					user.setRoles(userRole);
					user.setUserStatus(true);
				}
			}
			userRepository.save(user);
			LoginDto userDto = modelMapper.map(user, LoginDto.class);
			userDto.setRole(user.getRoles().getName());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("User registered successfully!", new HashMap<>() {
						{
							put("user", userDto);
						}
					}));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> forgotPassword(String email, HttpServletRequest servletRequest) {
		User user = userRepository.getUserByEmail(email);

		if (Objects.isNull(user)) {
			throw new NotFoundException("email khong ton tai");
		} else {
			String resetPassword = UUID.randomUUID().toString();
			String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest).replacePath(null).build()
					.toUriString();
			String fullName = user.getFirstName() + user.getLastName();
			String toEmail = user.getEmail();
			String subject = "Xác nhận tài khoản!";
			String text = "Chào " + fullName + ",\n"
					+ "Bạn cần xác nhận đó là bạn. Bấm vào đường dẫn bên dưới để xác nhận tài khoản: \n"
					+ "http://localhost:8080/api/auth/confirm-forgot-password?otp=" + resetPassword
					+ "\n" + "\nTrân trọng,\n" + "\nĐội ngũ LMS.";
			user.setCodeActive(resetPassword);
			userRepository.save(user);
			javaMail.sentEmail(toEmail, subject, text);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Reset-password success check mail", 200));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> login(LoginRequest loginRequest) {
		User user = userRepository.getUserByUsername(loginRequest.getUsername());

		if (Objects.isNull(user)) {
			throw new AuthException("Tai Khoan khong hop le");
		} else {
			if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
								loginRequest.getPassword()));
				if (user.isUserStatus() == false) {
					throw new AuthException("Tai khoan bi chan");
				} else {
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt = jwtTokenProvider.generateToken(authentication);
					UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

					List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
							.collect(Collectors.toList());

					LoginDto userDto = LoginDto.builder().email(userDetails.getEmail())
							.username(userDetails.getUsername()).role(roles.get(0)).userStatus(user.isUserStatus())
							.build();
					Map data = new HashMap<String, Object>();

					Date currentDate = new Date();

					Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

					data.put("userID", user.getId());
					data.put("user", userDto);
					data.put("expireDate", expireDate);
					data.put("token", "Bearer " + jwt);
					return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Sign in successfully", data));
				}
			} else {
				throw new BadRequestAlertException("mat khau current khong khop");
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> existAccountByUsername(String username) {
		Map data = new HashMap<String, Object>();
		if (userRepository.existsByUsername(username.trim())) {
			data.put("user", username + " đã tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("USERNAME_EXIST", data));
		} else {
			data.put("user", username + " chưa tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("USERNAME_NOT_EXIST", data));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> existAccountByEmail(String email) {
		Map data = new HashMap<String, Object>();
		if (userRepository.existsByEmail(email.trim())) {
			data.put("user", email + " đã tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("EMAIL_EXIST", data));
		} else {
			data.put("user", email + " chưa tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("EMAIL_NOT_EXIST", data));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Logout success full!", 200));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Logout success fail!", 400));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> confirmResetPassword(String token) {
		User user = userRepository.getUserByCodeActive(token);

		if (Objects.isNull(user)) {
			throw new NotFoundException("otp khong ton tai");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("confirm-reset success", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> changePassword(ChangePasswordRequest changePasswordRequest) {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (Objects.isNull(user)) {
			throw new NotFoundException("User khong ton tai");
		} else {
			if (passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
				user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
				userRepository.save(user);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Change password success full!", 200));
			} else {
				throw new BadRequestAlertException("mat khau current khong khop");
			}
		}
	}

	@Override
	public ResponseEntity<ApiResponse> resetPassword(ForgotPasswordRequest forgotPasswordRequest) {
		User user = userRepository.getUserByEmail(forgotPasswordRequest.getEmail());

		if (Objects.isNull(user)) {
			throw new NotFoundException("email khong ton tai");
		} else {
			user.setPassword(passwordEncoder.encode(forgotPasswordRequest.getPassword()));
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("reset-password success", 200));
		}
	}

}
