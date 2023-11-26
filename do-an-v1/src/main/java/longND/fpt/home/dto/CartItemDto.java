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
public class CartItemDto {
	private Long id;
	private Long cartId;
	private Long bookId;
	private String title;
	private String imageUrl;
	private int quantity;
	private double price;
	private double discountedPrice;
	private Long voucherId;
}
