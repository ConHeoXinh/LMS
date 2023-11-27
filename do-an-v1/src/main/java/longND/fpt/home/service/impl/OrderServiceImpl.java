package longND.fpt.home.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.dto.AuthorDto;
import longND.fpt.home.dto.BookDto;
import longND.fpt.home.dto.CartDto;
import longND.fpt.home.dto.CartItemDto;
import longND.fpt.home.dto.CustomPage;
import longND.fpt.home.dto.DepartmentDto;
import longND.fpt.home.dto.OrderDto;
import longND.fpt.home.dto.OrderItemDto;
import longND.fpt.home.dto.PublisherDto;
import longND.fpt.home.dto.SearchDto;
import longND.fpt.home.dto.UserDto;
import longND.fpt.home.dto.ViewSearchDto;
import longND.fpt.home.dto.VoucherDto;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.modal.Author;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;
import longND.fpt.home.modal.Department;
import longND.fpt.home.modal.Order;
import longND.fpt.home.modal.OrderItem;
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
import longND.fpt.home.request.ExtendBookRequest;
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
	@Autowired
	private ModelMapper modelMapper;

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

		Order oder = orderRepository.existsOrderByUseIdAndEmployeeIdEndOrderStatus(user.getId());
		if (!Objects.isNull(oder)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "order đã tồn tại");
		} else {
			List<OrderItem> orderItems = new ArrayList<>();

			for (CartItem item : cart.getCartItems()) {

				OrderItem orderItem = new OrderItem();

				orderItem.setBook(item.getBook());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(item.getPrice());
				orderItem.setDiscountedPrice(item.getDiscountedPrice());
				orderItem.setUser(item.getUser());
				orderItem.setVoucher(item.getVoucher());

				OrderItem createdOrderItem = orderItemRepository.save(orderItem);

				orderItems.add(createdOrderItem);
			}

			Order createdOrder = new Order();
			createdOrder.setUser(user);
			createdOrder.setOrderItems(orderItems);

			if(Objects.isNull(orderRequest.getCheckoutDate())) {
				throw new SaveDataException("Lam on chon ngay muon sach");
			}
			if(Objects.isNull(orderRequest.getReturnDate())) {
				throw new SaveDataException("Lam on chon ngay tra sach");
			}
			createdOrder.setCheckoutDate(convertStringToLocalDateTime(orderRequest.getCheckoutDate()));

			createdOrder.setReturnDate(convertStringToLocalDateTime(orderRequest.getReturnDate()));

			createdOrder.setTotalPrice(cart.getTotalPrice());
			createdOrder.setDiscounte(cart.getDiscounte());
			createdOrder.setOrderStatus(false);
			createdOrder.setTotalItem(cart.getTotalItem());
			createdOrder.setCreatedAt(LocalDateTime.now());
			createdOrder.setReturnOrder(false);
			createdOrder.setExtendOrder(0);

			cart.setOrdered(!cart.isOrdered());
			cartRepository.save(cart);
			Order savedOrder = orderRepository.save(createdOrder);

			for (OrderItem item : orderItems) {
				item.setOrder(savedOrder);
				orderItemRepository.save(item);

			}

			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add order success", 200));
		}

	}

	@Override
	public ResponseEntity<ObjectResponse> findOrderById(Long orderId) {

		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> usersOrderHistory(int indexPage) {

		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrderExpire(int indexPage) {

		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> confirmedOrder(Long orderId) {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		Order order = orderRepository.getById(orderId);

		if (Objects.isNull(order)) {
			throw new NotFoundException("not found order id");
		} else {
			order.setEmployee(user);

			List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(orderId);

			for (OrderItem item : orderItems) {
				item.setEmployee(user);
				orderItemRepository.save(item);
				Book book = bookRepository.findById(item.getBook().getId())
						.orElseThrow(() -> new NotFoundException("Book is not found"));
				book.setCopies(book.getCopies() + 1);
				book.setCopies_available(book.getCopies_available() - 1);

				bookRepository.save(book);
			}
			order.setOrderStatus(true);

			orderRepository.save(order);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("accept order success", 200));
	}

	@Override
	public ResponseEntity<ApiResponse> cancledOrder(Long orderId) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		Order order = orderRepository.getById(orderId);

		if (Objects.isNull(order)) {
			throw new NotFoundException("not found order id");
		} else {
			order.setEmployee(user);
			List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(orderId);

			for (OrderItem item : orderItems) {
				item.setEmployee(user);
				orderItemRepository.save(item);
			}
			order.setOrderStatus(false);
		}
		orderRepository.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("cancel order success", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrders(int indexPage) {
		int sizeItemOfPage = 4;
		int page = indexPage - 1;
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (!user.getRoles().getName().equals("ROLE_EMPLOYEE")) {
			throw new AuthException("access denied");
		} else {

			Pageable pageable = PageRequest.of(page, sizeItemOfPage);

			Page<Order> orders = orderRepository.findAllOrders(pageable);

			List<OrderDto> orderDtoList = new ArrayList<>();

			if (orders.getTotalElements() == 0) {
				throw new NotFoundException("not found orders");
			} else {
				for (Order order : orders.getContent()) {
					OrderDto orderDto = new OrderDto();
					orderDto.setOrderId(order.getId());

					orderDto.setCheckoutDate(order.getCheckoutDate());
					orderDto.setReturnDate(order.getReturnDate());
					orderDto.setTotalPrice(order.getTotalPrice());
					orderDto.setTotalDiscountedPrice(order.getTotalDiscountedPrice());
					orderDto.setDiscounte(order.getDiscounte());
					orderDto.setOrderStatus(order.isOrderStatus());
					orderDto.setTotalItem(order.getTotalItem());

					UserDto userDto = convertToUserDto(order.getUser());
					orderDto.setUserDto(userDto);

					orderDtoList.add(orderDto);
				}
			}

			CustomPage<OrderDto> pageResponse = new CustomPage<>(orderDtoList, indexPage, sizeItemOfPage,
					orders.getTotalElements(), orders.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Orders List", new HashMap<>() {
				{
					put("searchList", pageResponse);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getOrderDetail(Long orderId) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (!user.getRoles().getName().equals("ROLE_EMPLOYEE")) {
			throw new AuthException("access denied");
		} else {
			Order order = orderRepository.getById(orderId);
			if (Objects.isNull(order)) {
				throw new NotFoundException("not found orders");
			} else {
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderId(order.getId());

				orderDto.setCheckoutDate(order.getCheckoutDate());
				orderDto.setReturnDate(order.getReturnDate());
				orderDto.setTotalPrice(order.getTotalPrice());
				orderDto.setTotalDiscountedPrice(order.getTotalDiscountedPrice());
				orderDto.setDiscounte(order.getDiscounte());
				orderDto.setOrderStatus(order.isOrderStatus());
				orderDto.setTotalItem(order.getTotalItem());

				UserDto userDto = convertToUserDto(order.getUser());
				orderDto.setUserDto(userDto);
				List<OrderItem> list = orderItemRepository.findAllOrderItemsByOrderId(orderId);
				List<OrderItemDto> listDto = convertToOrderItemDto(list);

				return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Order", new HashMap<>() {
					{
						put("orderDetials", orderDto);
						put("orderItems", listDto);
					}
				}));
			}
		}
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

			if (!Objects.isNull(cartItemDto.getVoucherId())) {
				Voucher voucher = voucherRepository.findById(cartItemDto.getVoucherId())
						.orElseThrow(() -> new NotFoundException("Voucher is not found"));
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

	private LocalDateTime convertStringToLocalDateTime(String date) {

		// Define the date format pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Parse the date string to a LocalDate (date-only)
		LocalDate localDate = LocalDate.parse(date, formatter);

		// Create a LocalDateTime by combining the date with a default time (e.g.,
		// midnight)
		LocalDateTime localDateTime1 = localDate.atTime(LocalTime.MIDNIGHT);

		return localDateTime1;
	}

	private List<OrderItemDto> convertToOrderItemDto(List<OrderItem> orderItems) {
		List<OrderItemDto> list = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDto orderItemDto = new OrderItemDto();

			orderItemDto.setOrderId(item.getOrder().getId());
			orderItemDto.setOrderItemId(item.getId());

			BookDto bookDto = new BookDto();
			bookDto = convertToBookDto(item.getBook());

			orderItemDto.setBookdto(bookDto);
			orderItemDto.setQuantity(item.getQuantity());
			orderItemDto.setPrice(item.getPrice());
			orderItemDto.setDiscountedPrice(item.getDiscountedPrice());

//			UserDto employeeDto = convertToUserDto(item.getEmployee());
//			orderItemDto.setEmployeeDto(employeeDto);

			UserDto userDto = convertToUserDto(item.getUser());
			orderItemDto.setUserDto(userDto);

			VoucherDto voucherDto = new VoucherDto();

			if (!Objects.isNull(item.getVoucher())) {

				voucherDto.setIdVoucher(item.getVoucher().getId());
				voucherDto.setNameVoucher(item.getVoucher().getCode());
				voucherDto.setDescription(item.getVoucher().getDescription());
				voucherDto.setPercent(item.getVoucher().getPercent());
				voucherDto.setDueDate(item.getVoucher().getDueDay());
				voucherDto.setStatus(item.getVoucher().getStatus());
				voucherDto.setUserId(item.getVoucher().getUser().getId());

				orderItemDto.setVoucherDto(voucherDto);
			}

			list.add(orderItemDto);
		}
		return list;
	}

	private UserDto convertToUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setUsername(user.getUsername());
		userDto.setDob(user.getDob());
		userDto.setGender(user.isGender());
		userDto.setUserStatus(user.isUserStatus());
		userDto.setCreateAt(user.getCreateAt());
		userDto.setRoles(user.getRoles().getName());

		return userDto;
	}

	private BookDto convertToBookDto(Book book) {
		BookDto bookDto = new BookDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		bookDto.setAuthors(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		bookDto.setDepartments(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		bookDto.setPublisher(publisherDto);

		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setPrice(book.getPrice());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setCreateAt(book.getCreateAt());
		bookDto.setCopies(book.getCopies());
		bookDto.setCopies_available(book.getCopies_available());
		bookDto.setLanguage(book.getLanguage());
		bookDto.setForUser(book.isForUser());
		bookDto.setPage(book.getPage());
		bookDto.setAuthors(authorDtolist);
		bookDto.setDepartments(departmentDtolist);
		bookDto.setPublisher(publisherDto);
		return bookDto;
	}

	@Override
	public ResponseEntity<ApiResponse> extendOrder(ExtendBookRequest extendBookRequest) {
		Order order = orderRepository.findById(extendBookRequest.getOrderId())
				.orElseThrow(() -> new NotFoundException("Order is not found"));
		order.setReturnDate(extendBookRequest.getReturnDate());
		int currentExtend = order.getExtendOrder();
		if (currentExtend >= 2) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("sách đã hết lượt gia hạn", 500));
		}

		order.setExtendOrder(currentExtend + 1);

		orderRepository.save(order);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("gia hạn sách thành công", 200));

	}
}
