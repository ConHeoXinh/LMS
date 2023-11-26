package longND.fpt.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.request.CreateOrderRequest;
import longND.fpt.home.request.ExtendBookRequest;
import longND.fpt.home.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createOrderHandler(@RequestBody CreateOrderRequest orderRequest) {
		return orderService.createOrder(orderRequest);
	}

	@PutMapping("/{orderId}/confirmed")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> ConfirmedOrderHandler(@PathVariable Long orderId) {
		return orderService.confirmedOrder(orderId);
	}

	@PutMapping("/{orderId}/cancel")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> canceledOrderHandler(@PathVariable Long orderId) {
		return orderService.cancledOrder(orderId);
	}

	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getAllOrdersHandler(@RequestParam("index-page") int indexPage) {
		return orderService.getAllOrders(indexPage);
	}
	
	@GetMapping("/{orderId}")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getDetailOrdersHandler(@PathVariable Long orderId) {
		return orderService.getOrderDetail(orderId);
	}
	
	@GetMapping("/expire")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getAllOrdersExprieHandler(@RequestParam("index-page") int indexPage) {
		return orderService.getAllOrderExpire(indexPage);
	}
	
	@PostMapping("/extend-book")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> extendBook(@RequestBody ExtendBookRequest extendBookRequest) {
		return orderService.extendOrder(extendBookRequest);
	}

}
