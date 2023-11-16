package longND.fpt.home.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage<T> {
	private List<T> content;
	private int page;
	private int size;
	private Long totalElements;
	private int totalPage;

}
