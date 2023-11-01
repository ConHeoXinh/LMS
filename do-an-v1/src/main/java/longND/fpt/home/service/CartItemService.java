package longND.fpt.home.service;

import org.springframework.stereotype.Service;

import longND.fpt.home.dto.CartDto;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;

@Service
public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem);

	public CartItem isCartItemExist(Cart cart, Book book, Long userId);

	public CartDto removeCartItem(Long userId, Long cartItemId);

	public CartItem findCartItemById(Long cartItemId);
}
