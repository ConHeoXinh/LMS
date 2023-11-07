package longND.fpt.home.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewSearchDto {
	private Long bookId;
	private String title;
	private String description;
	private double price;
	private String imageUrl;
	private int copies;
	private int copies_available;

}
