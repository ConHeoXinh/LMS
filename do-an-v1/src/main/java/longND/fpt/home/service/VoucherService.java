package longND.fpt.home.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.request.VoucherRequest;
import longND.fpt.home.response.ApiResponse;
import longND.fpt.home.response.JwtResponse;
import longND.fpt.home.response.ObjectResponse;

@Service
public interface VoucherService {

	public ResponseEntity<ObjectResponse> getAllVoucher();

	public ResponseEntity<ObjectResponse> getOneVoucher(Long id);

	public ResponseEntity<ApiResponse> insertVoucher(List<VoucherRequest> voucherRequest);

	public ResponseEntity<ObjectResponse> updateVoucher(Long id, VoucherRequest voucherRequest, Long userID);

	public ResponseEntity<ObjectResponse> updateStatusVoucher(Long id, int status);

	public ResponseEntity<JwtResponse> getAllVoucherByUserID();

	public ResponseEntity<ObjectResponse> addVoucherByUserID(Long userID);
}
