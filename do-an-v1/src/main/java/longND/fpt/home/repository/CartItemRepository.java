package longND.fpt.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Cart;
import longND.fpt.home.modal.CartItem;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//	@Query(value = "SELECT * FROM cart_item WHERE (cart_id IS NULL OR cart_id=:cart) AND (book_id IS NULL OR book_id=:book)AND  (user_id IS NULL OR user_id =:userId)", nativeQuery = true)
//	@Query(value = "SELECT c FROM cart_item c WHERE (:#{#cart.id} IS NULL OR c.cart_id = :#{#cart.id})"+ "AND (:#{#book.id} IS NULL OR c.book_id = :#{#book.id})"
//			+ "AND (user_id IS NULL OR c.user_id = :userId)", nativeQuery = true)
	@Query(value = "SELECT * FROM cart_item WHERE cart_id=:cart AND book_id=:book AND user_id =:userId", nativeQuery = true)
	public CartItem isCartItemExist(@Param("cart") Long cartId, @Param("book") Long bookId,
			@Param("userId") Long userId);

	@Query(value = "DELETE FROM cart_item WHERE id=:id", nativeQuery = true)
	public void deleteCartItemById(@Param("id") Long id);
}
