package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.response.ObjectResponse;

@Service
public interface FavoriteSevice {
	public ResponseEntity<ObjectResponse> createLikeBookByUser(Long bookId);

	public ResponseEntity<ObjectResponse> editLikeBookByUer(Long favoriteId, boolean type);

	public ResponseEntity<ObjectResponse> getAllBookFavoriteByUserId();
}
