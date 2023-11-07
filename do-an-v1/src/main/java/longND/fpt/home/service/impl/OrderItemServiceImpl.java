package longND.fpt.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.modal.OrderItem;
import longND.fpt.home.repository.OrderItemRepository;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public ResponseEntity<ApiResponse> createOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("add order item success", 200));
	}

}
