package longND.fpt.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private String status;
	private Object object;
}
