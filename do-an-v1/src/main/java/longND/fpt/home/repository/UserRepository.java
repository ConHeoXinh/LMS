package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import longND.fpt.home.modal.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Boolean existsByPassword(String password);

	User getUserByCodeActive(String otp);

	User getUserByEmail(String email);

	User findUserById(Long id);

	User getUserByUsername(String username);

	@Query(value = "SELECT count(id) FROM User ")
	int totalAccount();
}
