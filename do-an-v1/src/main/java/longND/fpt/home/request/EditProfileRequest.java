package longND.fpt.home.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileRequest {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private boolean gender;
	private String dob;
}
