package longND.fpt.home.dto;

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
public class OrderItemDto {

	private Long orderItemId;

	private Long orderId;

	private BookDto bookdto;

	private int quantity;

	private double price;

	private double discountedPrice;

	private UserDto employeeDto;

	private UserDto userDto;

	private VoucherDto voucherDto;
}
