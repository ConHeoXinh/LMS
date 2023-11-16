package longND.fpt.home.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String username;
	private LocalDateTime dob;
	private boolean gender;
	private boolean userStatus;
	private LocalDateTime createAt;
	private String roles;
	private String imageUrl;
}
