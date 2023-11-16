package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	Optional<Publisher> findByName(String name);

	Publisher getById(Long id);

	boolean existsByName(String name);

	@Query(value = "SELECT * FROM publisher WHERE :publisherName IS NULL OR publisher.name LIKE %:publisherName%", countQuery = "SELECT COUNT(id) FROM publisher WHERE :publisherName IS NULL OR publisher.name LIKE %:publisherName%", nativeQuery = true)
	Page<Publisher> listPublisher(@Param("publisherName") String publisherName, Pageable pageable);

	@Query(value = "SELECT * FROM publisher", countQuery = "SELECT COUNT(id) FROM publisher", nativeQuery = true)
	Page<Publisher> getListPublisher(Pageable pageable);
}
