package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.modal.User;
import longND.fpt.home.request.CreateOrderRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface OrderService {
	public ResponseEntity<ApiResponse> createOrder(CreateOrderRequest orderRequest);

	public ResponseEntity<ObjectResponse> findOrderById(Long orderId);

	public ResponseEntity<ObjectResponse> getAllOrdersByUsername(String username);

	public ResponseEntity<ObjectResponse> usersOrderHistory(int indexPage);

	public ResponseEntity<ApiResponse> confirmedOrder(Long orderId);

	public ResponseEntity<ApiResponse> cancledOrder(Long orderId);

	public ResponseEntity<ObjectResponse> getAllOrders(int indexPage);
	
	public ResponseEntity<ObjectResponse> getOrderDetail(Long orderId);

	public ResponseEntity<ApiResponse> deleteOrder(Long orderId);
}
