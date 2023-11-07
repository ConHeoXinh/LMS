package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.CartDto;
import longND.fpt.home.dto.CartItemDto;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;
import longND.fpt.home.modal.Order;
import longND.fpt.home.modal.OrderItem;
import longND.fpt.home.modal.Role;
import longND.fpt.home.modal.User;
import longND.fpt.home.modal.Voucher;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.repository.CartRepository;
import longND.fpt.home.repository.OrderItemRepository;
import longND.fpt.home.repository.OrderRepository;
import longND.fpt.home.repository.RoleRepository;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.repository.VoucherRepository;
import longND.fpt.home.request.CreateOrderRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.CartService;
import longND.fpt.home.service.OrderItemService;
import longND.fpt.home.service.OrderService;
import longND.fpt.home.util.SecurityUtils;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ResponseEntity<ApiResponse> createOrder(CreateOrderRequest orderRequest) {

//		User userFind = userRepository.getUserByEmail(orderRequest.getEmail());
//		// case: employee create order for guest
//		if (Objects.isNull(userFind)) {
//			User guest = new User();
//
//			guest.setFirstName(orderRequest.getFirstName());
//			guest.setLastName(orderRequest.getLastName());
//			guest.setEmail(orderRequest.getEmail());
//			guest.setPhoneNumber(orderRequest.getPhoneNumber());
//			Role guestRole = roleRepository.findByName("ROLE_GUEST")
//					.orElseThrow(() -> new NotFoundException("User role is not found"));
//			user.setUserStatus(false);
//			guest.setRoles(guestRole);
//			
//			
//		}
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		Cart cart = transferDataCart(cartService.findUserCart(user.getId()));

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem item : cart.getCartItems()) {

			OrderItem orderItem = new OrderItem();

			orderItem.setBook(item.getBook());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(item.getPrice());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());

			OrderItem createdOrderItem = orderItemRepository.save(orderItem);

			orderItems.add(createdOrderItem);
		}

		Order createdOrder = new Order();
		createdOrder.setUsers(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setCheckoutDate(orderRequest.getCheckoutDate());
		createdOrder.setReturnDate(orderRequest.getReturnDate());
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setDiscounte(cart.getDiscounte());
		createdOrder.setOrderStatus(false);
		createdOrder.setTotalItem(cart.getTotalItem());
		createdOrder.setCreatedAt(LocalDateTime.now());

		Order savedOrder = orderRepository.save(createdOrder);

		for (OrderItem item : orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);

		}

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add order success", 200));

	}

	@Override
	public ResponseEntity<ObjectResponse> findOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> usersOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> confirmedOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> cancledOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> deleteOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<CartItem> transferDataCartItem(List<CartItemDto> cartItemDtos) {
		List<CartItem> cartItems = new ArrayList<>();
		for (CartItemDto cartItemDto : cartItemDtos) {
			CartItem cartItem = new CartItem();
			cartItem.setId(cartItemDto.getId());
			Cart cart = cartRepository.findById(cartItemDto.getCartId())
					.orElseThrow(() -> new NotFoundException("Cart is not found"));
			cartItem.setCart(cart);
			Book book = bookRepository.getBookById(cartItemDto.getBookId());
			cartItem.setBook(book);
			cartItem.setUser(cart.getUser());
			Voucher voucher = voucherRepository.findById(cartItemDto.getVoucherId())
					.orElseThrow(() -> new NotFoundException("Voucher is not found"));
			if (!Objects.isNull(voucher)) {
				cartItem.setVoucher(voucher);
			}
			cartItem.setPrice(cartItemDto.getPrice());
			cartItem.setDiscountedPrice(cartItemDto.getDiscountedPrice());
			cartItem.setQuantity(cartItemDto.getQuantity());
			cartItems.add(cartItem);
		}
		return cartItems;
	}

	private Cart transferDataCart(CartDto cartDto) {
		Cart cart = new Cart();
		cart.setId(cartDto.getId());

		User user = userRepository.findUserById(cartDto.getUserId());
		cart.setUser(user);
		cart.setCartItems(transferDataCartItem(cartDto.getCartItemDtos()));
		cart.setTotalPrice(cartDto.getTotalPrice());
		cart.setTotalItem(cartDto.getTotalItem());
		cart.setTotalDiscountedPrice(cartDto.getTotalDiscountedPrice());
		cart.setDiscounte(cartDto.getDiscounte());

		return cart;
	}
}
