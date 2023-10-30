package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	Optional<Publisher> findByName(String name);

	Publisher getById(Long id);

	Boolean existsByName(String name);
}
