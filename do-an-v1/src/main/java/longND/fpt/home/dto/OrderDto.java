package longND.fpt.home.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class OrderDto {

	private Long OrderId;

	private LocalDateTime checkoutDate;

	private LocalDateTime returnDate;

	private double totalPrice;

	private double totalDiscountedPrice;

	private double discounte;

	private boolean orderStatus;

	private int totalItem;

	private UserDto userDto;
	private UserDto employeeDto;
}
