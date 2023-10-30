package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByName(String name);

	Department getById(Long id);

	Boolean existsByName(String name);
}
