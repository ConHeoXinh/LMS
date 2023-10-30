package longND.fpt.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	@Query(value = "SELECT * FROM favorite WHERE user_id =:userId", nativeQuery = true)
	List<Favorite> findFavoriteByUserId(@Param("userId") Long userId);

	Favorite getById(Long id);
}
