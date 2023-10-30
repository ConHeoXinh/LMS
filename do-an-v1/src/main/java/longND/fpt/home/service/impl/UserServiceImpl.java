package longND.fpt.home.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.UserDto;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.User;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.request.EditProfileRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.JwtResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.UserService;
import longND.fpt.home.util.SecurityUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ObjectResponse> findUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<JwtResponse> profile() {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (Objects.isNull(user)) {
			throw new NotFoundException("User khong ton tai");
		} else {
			UserDto dto = UserDto.builder().id(user.getId()).username(user.getUsername()).createAt(user.getCreateAt())
					.dob(user.getDob()).phoneNumber(user.getPhoneNumber()).firstName(user.getFirstName())
					.lastName(user.getLastName()).email(user.getEmail()).userStatus(user.isUserStatus())
					.roles(user.getRoles().getName()).build();

			return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(HttpStatus.OK.name(), dto));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editProfile(EditProfileRequest editProfileRequest) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		user.setFirstName(editProfileRequest.getFirstName());
		user.setLastName(editProfileRequest.getLastName());
		user.setPhoneNumber(editProfileRequest.getPhoneNumber());
		user.setEmail(editProfileRequest.getEmail());

		String utilDate = editProfileRequest.getDob();

		// Define the date format pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Parse the date string to a LocalDate (date-only)
		LocalDate localDate = LocalDate.parse(utilDate, formatter);

		// Create a LocalDateTime by combining the date with a default time (e.g.,
		// midnight)
		LocalDateTime localDateTime = localDate.atTime(LocalTime.MIDNIGHT);

		user.setDob(localDateTime);
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("edit profile thanh cong", 200));
	}

}
