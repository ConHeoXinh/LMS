package longND.fpt.home.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.modal.CartItem;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CartDto {
	private Long id;
	private Long userId;
	private List<CartItemDto> cartItemDtos;
	private double totalPrice;
	private int totalItem;
	private double totalDiscountedPrice;
	private double discounte;
}
