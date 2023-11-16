package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByName(String name);

	Author getById(Long id);

	Boolean existsByName(String name);

	@Query(value = "SELECT * FROM author WHERE :authorName IS NULL OR author.name LIKE %:authorName%", countQuery = "SELECT COUNT(id) FROM author WHERE :authorName IS NULL OR author.name LIKE %:authorName%", nativeQuery = true)
	Page<Author> listAuthor(@Param("authorName") String authorName, Pageable pageable);

	@Query(value = "SELECT * FROM author", countQuery = "SELECT COUNT(id) FROM author", nativeQuery = true)
	Page<Author> getListAuthor(Pageable pageable);
}
