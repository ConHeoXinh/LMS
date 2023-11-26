package longND.fpt.home.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Order;
import longND.fpt.home.modal.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT * FROM orderbook WHERE user_id = :userId AND employee_id IS NULL AND order_status = false", nativeQuery = true)
	Order existsOrderByUseIdAndEmployeeIdEndOrderStatus(@Param("userId") Long userId);

	Optional<Order> findByUserId(Long userId);

	Order getById(Long id);

	@Query(value = "SELECT * FROM orderbook WHERE employee_id IS NULL AND order_status = false",
			countQuery = "SELECT COUNT(orderbook.id) FROM orderbook WHERE employee_id IS NULL AND order_status = false",
			nativeQuery = true)
	Page<Order> findAllOrders(Pageable pageable);
	
	@Query(value = "SELECT * FROM organica.orderbook ORDER BY return_date DESC",
			countQuery = "SELECT * FROM organica.orderbook ORDER BY return_date DESC",
			nativeQuery = true)
	Page<Order> findAllOrdersExpire(Pageable pageable);
	
	List<Order> findAllByUser(User user);
}
