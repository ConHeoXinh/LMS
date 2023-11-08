package longND.fpt.home.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterRequest {

//	private Long authorId;
//	private Long departmentId;
//	private Long publisherId;
//	private double minPrice;
//	private double maxPrice;
//	private int statusSortPrice;

	private double minPrice;

	private double maxPrice;

	private String author;

	private String department;

	private String publisher;

	private int statusSortPrice;

}
