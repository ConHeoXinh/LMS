package longND.fpt.home.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByName(String name);

	Department getById(Long id);

	Boolean existsByName(String name);

	@Query(value = "SELECT * FROM departmment WHERE :departmentName IS NULL OR departmment.name LIKE %:departmentName%", countQuery = "SELECT COUNT(id) FROM departmment WHERE :departmentName IS NULL OR departmment.name LIKE %:departmentName%", nativeQuery = true)
	Page<Department> listDepartment(@Param("departmentName") String departmentName, Pageable pageable);

	@Query(value = "SELECT * FROM departmment", countQuery = "SELECT COUNT(id) FROM departmment", nativeQuery = true)
	Page<Department> getListDepartment(Pageable pageable);
}
