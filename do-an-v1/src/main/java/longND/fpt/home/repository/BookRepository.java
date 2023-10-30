package longND.fpt.home.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book getBookById(Long id);

	boolean existsByTitle(String title);

//	@Query("SELECT * FROM book")
//	List<Book> getAllBookNonUser();

	@Query(value = "SELECT * From book where LOWER(book.title) Like %:query%", nativeQuery = true)
	List<Book> searchBook(@Param("query") String query);
}
