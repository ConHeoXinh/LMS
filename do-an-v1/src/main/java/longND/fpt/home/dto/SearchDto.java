package longND.fpt.home.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SearchDto {

	private Long bookId;
	private String title;
	private String desciption;
	private double price;
	private String imageUrl;
	private int copies;
	private int copies_available;
	private String language;
	private List<DepartmentDto> departmentDtoList;
	private List<AuthorDto> authorDtoList;
	private PublisherDto publisheDto;

}
