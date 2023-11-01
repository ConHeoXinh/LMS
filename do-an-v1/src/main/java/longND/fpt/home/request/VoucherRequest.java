package longND.fpt.home.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {

	private String name;
	private String description;
	private int percent;
	private int dueDate;
	private int userId;
}
