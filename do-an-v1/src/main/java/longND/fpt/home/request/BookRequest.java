package longND.fpt.home.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter

public class BookRequest {

	private String title;

	private String description;

	private double price;
	
//	private MultipartFile imageUrl;

	private LocalDateTime createAt;

	private int copies_available;

	private String language;

	private boolean forUser;

	private int page;

	private List<Long> authors;

	private List<Long> departments;

	private Long publisher;
}
