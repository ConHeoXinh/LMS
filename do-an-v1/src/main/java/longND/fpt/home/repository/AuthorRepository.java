package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByName(String name);

	Author getById(Long id);

	Boolean existsByName(String name);
}
