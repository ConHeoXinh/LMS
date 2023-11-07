package longND.fpt.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//	@Query(value = "SELECT * FROM Order WHERE user_id = :userId", nativeQuery = true)
//	List<Order> getUsersOrders(@Param("userId") Long userId);
}
