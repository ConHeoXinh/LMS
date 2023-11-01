package longND.fpt.home.service;

import org.springframework.stereotype.Service;

import longND.fpt.home.dto.CartDto;
import longND.fpt.home.dto.CartItemDto;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.User;
import longND.fpt.home.request.AddItemRequest;

@Service
public interface CartService {
	public Cart createCart(User user);

	public CartItemDto addCartItem(Long userId, AddItemRequest req);

	public CartDto findUserCart(Long userId);

}
