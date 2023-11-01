package longND.fpt.home.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.modal.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

	Voucher getVoucherById(Long id);

	Optional<Voucher> findVoucherById(Long id);

	@Query(value = "SELECT * FROM voucher WHERE user_id=:userID AND status = :statusI", nativeQuery = true)
	List<Voucher> getVoucherByUser_IdAndStatus(@Param("userID") Long userID, @Param("statusI") int status);

	Voucher getVoucherByUser_Id(Long userID);

	// co su nham lan xu li giua employee voi user
	Voucher getVoucherByIdAndUser_Id(Long voucherID, Long userID);

	List<Voucher> getVoucherByStatus(int status);

	Boolean existsByCode(String code);
}
