package longND.fpt.home.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
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

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department bd ON book.id = bd.book_id\r\n"
			+ "LEFT JOIN departmment ON bd.department_id = departmment.id\r\n"
			+ "LEFT JOIN book_author ba ON book.id = ba.book_id\r\n"
			+ "LEFT JOIN author ON ba.author_id = author.id\r\n"
			+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\r\n" + "GROUP BY book.id", nativeQuery = true)
	List<Book> listSearchFilter();

	@Query(value = "SELECT * From book where LOWER(book.title) Like %:query%", nativeQuery = true)
	List<Book> listSearchBookByTitle(@Param("query") String query);

	@Query(value = "SELECT *  FROM book where LOWER(book.title) Like %:title%", nativeQuery = true)
	List<Book> searchBookByTitle(@Param("title") String title, PageRequest pageRequest);

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department ON book.id = book_id\r\n"
			+ "LEFT JOIN departmment ON department_id = departmment.id\r\n"
			+ "WHERE departmment.id = :departmentId", nativeQuery = true)
	List<Book> relatedBook(@Param("departmentId") Long departmentId);
}
