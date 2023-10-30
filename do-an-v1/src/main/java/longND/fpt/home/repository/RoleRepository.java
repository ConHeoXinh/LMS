package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

	Boolean existsByName(String name);
}
