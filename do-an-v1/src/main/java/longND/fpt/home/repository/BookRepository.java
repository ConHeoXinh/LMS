package longND.fpt.home.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book getBookById(Long id);

	boolean existsByTitle(String title);

	@Query(value = "SELECT * FROM book ORDER BY id DESC LIMIT 10", nativeQuery = true)
	List<Book> getTop10BookNew();

	@Query(value = "SELECT * FROM book where copies > 0 order by copies desc limit 10", nativeQuery = true)
	List<Book> getTop10BookBorrowed();

	@Query(value = "SELECT * From book where LOWER(book.title) Like %:query%", nativeQuery = true)
	List<Book> listSearchBookByTitle(@Param("query") String query);

	@Query(value = "SELECT *  FROM book where LOWER(book.title) Like %:title%", nativeQuery = true)
	List<Book> searchBookByTitle(@Param("title") String title, PageRequest pageRequest);

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department ON book.id = book_id\r\n"
			+ "LEFT JOIN departmment ON department_id = departmment.id\r\n"
			+ "WHERE departmment.id = :departmentId", nativeQuery = true)
	List<Book> relatedBook(@Param("departmentId") Long departmentId);

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department bd ON book.id = bd.book_id\r\n"
			+ "LEFT JOIN departmment ON bd.department_id = departmment.id\r\n"
			+ "LEFT JOIN book_author ba ON book.id = ba.book_id\r\n"
			+ "LEFT JOIN author ON ba.author_id = author.id\r\n"
			+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\r\n" + "WHERE (book.price >0)\r\n"
			+ "AND (book.copies_available > 0)\r\n" + "AND (:authorName IS NULL OR author.name LIKE %:authorName%)\r\n"
			+ "AND (:departName IS NULL OR departmment.name LIKE %:departName%)\r\n"
			+ "AND (:publisherName IS NULL OR publisher.name LIKE %:publisherName%)\r\n"
			+ "GROUP BY book.id", countQuery = "SELECT COUNT(book.id) FROM book\r\n"
					+ "LEFT JOIN book_department bd ON book.id = bd.book_id\r\n"
					+ "LEFT JOIN departmment ON bd.department_id = departmment.id\r\n"
					+ "LEFT JOIN book_author ba ON book.id = ba.book_id\r\n"
					+ "LEFT JOIN author ON ba.author_id = author.id\r\n"
					+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\r\n" + "WHERE (book.price >0)\r\n"
					+ "AND (book.copies_available > 0)\r\n"
					+ "AND (:authorName IS NULL OR author.name LIKE %:authorName%)\r\n"
					+ "AND (:departName IS NULL OR departmment.name LIKE %:departName%)\r\n"
					+ "AND (:publisherName IS NULL OR publisher.name LIKE %:publisherName%)\r\n"
					+ "GROUP BY book.id", nativeQuery = true)
	Page<Book> listSearchFilter(@Param("authorName") String authorName, @Param("departName") String departName,
			@Param("publisherName") String publisherName, Pageable pageable);

	@Query(value = "SELECT book.* FROM organica.book LEFT JOIN book_author ba ON book.id =ba.book_id LEFT JOIN author ON ba.author_id = author.id WHERE author.id = :idAuthor", countQuery = "SELECT COUNT(book.id) FROM organica.book LEFT JOIN book_author ba ON book.id =ba.book_id LEFT JOIN author ON ba.author_id = author.id WHERE author.id = :idAuthor", nativeQuery = true)
	Page<Book> listBookByAuthorId(@Param("idAuthor") Long idAuthor, Pageable pageable);

	@Query(value = "SELECT book.* FROM organica.book LEFT JOIN book_department bd ON book.id =bd.book_id LEFT JOIN departmment ON bd.department_id = departmment.id WHERE departmment.id = :idDepartment", countQuery = "SELECT COUNT(book.id) FROM organica.book LEFT JOIN book_department bd ON book.id =bd.book_id LEFT JOIN departmment ON bd.department_id = departmment.id WHERE departmment.id = :idDepartment", nativeQuery = true)
	Page<Book> listBookByDepartmentId(@Param("idDepartment") Long idDepartment, Pageable pageable);
}
