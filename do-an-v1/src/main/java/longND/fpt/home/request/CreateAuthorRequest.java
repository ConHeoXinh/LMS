package longND.fpt.home.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAuthorRequest {
	private String name;
	private String imageUrl;
	private String description;
}
