package longND.fpt.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.dto.CartDto;
import longND.fpt.home.dto.CartItemDto;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;
import longND.fpt.home.request.AddItemRequest;
import longND.fpt.home.service.CartService;
import longND.fpt.home.util.SecurityUtils;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CartDto> findUserCartHandler() {

		Long userId = SecurityUtils.getPrincipal().getId();

		CartDto cart = cartService.findUserCart(userId);

		return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
	}

	@PutMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CartItemDto> addItemToCart(@RequestBody AddItemRequest req) {

		Long userId = SecurityUtils.getPrincipal().getId();
		System.out.println("user id: " + userId);
		CartItemDto createdCartItem = cartService.addCartItem(userId, req);

		return new ResponseEntity<>(createdCartItem, HttpStatus.ACCEPTED);

	}
}
