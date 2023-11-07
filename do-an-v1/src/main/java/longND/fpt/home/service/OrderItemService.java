package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.modal.OrderItem;
import longND.fpt.home.response.ApiResponse;

@Service
public interface OrderItemService {
	public ResponseEntity<ApiResponse> createOrderItem(OrderItem orderItem);
}
