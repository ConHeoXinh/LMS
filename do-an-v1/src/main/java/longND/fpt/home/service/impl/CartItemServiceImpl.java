package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.CartDto;
import longND.fpt.home.dto.CartItemDto;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;
import longND.fpt.home.modal.User;
import longND.fpt.home.repository.CartItemRepository;
import longND.fpt.home.repository.CartRepository;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
		if (Objects.isNull(cartItem.getVoucher())) {
			cartItem.setVoucher(null);
			cartItem.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
		} else {
			double discountPrice = ((double) cartItem.getVoucher().getPercent()) / 100;
			cartItem.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity() * (1 - discountPrice));
		}
		CartItem createdCartItem = cartItemRepository.save(cartItem);

		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) {

		CartItem item = findCartItemById(id);
		User user = userRepository.findUserById(item.getUser().getId());

		if (user.getId().equals(userId)) {

			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity() * item.getBook().getPrice());

			if (Objects.isNull(cartItem.getVoucher())) {
				item.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
			} else {
				double discountPrice = cartItem.getVoucher().getPercent() / 100;
				item.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity() * discountPrice);
			}

			return cartItemRepository.save(item);

		} else {
			throw new NotFoundException("You can't update  another users cart_item");
		}

	}

	@Override
	public CartItem isCartItemExist(Cart cart, Book book, Long userId) {
		CartItem cartItem = new CartItem();

		cartItem = cartItemRepository.isCartItemExist(cart.getId(), book.getId(), userId);

		return cartItem;
	}

	@Override
	public CartDto removeCartItem(Long userId, Long cartItemId) {

		System.out.println("userId- " + userId + " cartItemId " + cartItemId);

		CartItem cartItem = findCartItemById(cartItemId);
		User user = userRepository.findUserById(cartItem.getUser().getId());
		User reqUser = userRepository.findUserById(userId);

		if (user.getId().equals(reqUser.getId())) {

			Cart cart = cartRepository.findByUserId(userId);

			List<CartItem> cartItems = cart.getCartItems();

			cartItems.remove(cartItem);

			cartItemRepository.deleteById(cartItemId);

			double totalPrice = 0;
			double totalItem = (double) cartItems.size();
			double totalDiscountedPrice = 0;

			for (CartItem cartItem1 : cartItems) {
				totalPrice += cartItem1.getPrice();
				totalDiscountedPrice += cartItem1.getDiscountedPrice();
			}

			cart.setUser(reqUser);
			cart.setCartItems(cartItems);
			cart.setTotalPrice(totalPrice);
			cart.setTotalItem(totalItem);
			cart.setTotalDiscountedPrice(totalDiscountedPrice);

			cartRepository.save(cart);

			CartDto cartDto = new CartDto();
			List<CartItemDto> cartItemDtos = new ArrayList<>();

			for (CartItem cartItem2 : cartItems) {
				CartItemDto cartItemDto = modelMapper.map(cartItem2, CartItemDto.class);
				cartItemDtos.add(cartItemDto);
			}
			cartDto.setId(cart.getId());

			cartDto.setUserId(cart.getUser().getId());
			cartDto.setCartItemDtos(cartItemDtos);
			cartDto.setTotalPrice(cart.getTotalPrice());
			cartDto.setTotalItem(cart.getTotalItem());
			cartDto.setTotalDiscountedPrice(totalDiscountedPrice);
			cartDto.setDiscounte(cart.getDiscounte());

			return cartDto;
		} else {
			throw new NotFoundException("you can't remove anothor users item");
		}

	}

	@Override
	public CartItem findCartItemById(Long cartItemId) {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new NotFoundException("cartItem not found with id : " + cartItemId);
	}

}
