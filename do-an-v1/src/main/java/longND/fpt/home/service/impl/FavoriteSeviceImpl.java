package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.exception.UpdateDataException;
import longND.fpt.home.modal.Book;
import longND.fpt.home.modal.Favorite;
import longND.fpt.home.modal.User;
import longND.fpt.home.repository.BookRepository;
import longND.fpt.home.repository.FavoriteRepository;
import longND.fpt.home.repository.UserRepository;
import longND.fpt.home.response.ObjectResponse;
import longND.fpt.home.service.FavoriteSevice;
import longND.fpt.home.util.SecurityUtils;

@Service
public class FavoriteSeviceImpl implements FavoriteSevice {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public ResponseEntity<ObjectResponse> createLikeBookByUser(Long bookId) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		Book book = bookRepository.getBookById(bookId);

		if (Objects.isNull(book)) {
			throw new NotFoundException("book_id khong ton tai");
		} else {

			Favorite favorites = Favorite.builder().book(book).user(user).isFavorite(true).build();

			Favorite save = favoriteRepository.save(favorites);

			if (Objects.isNull(save)) {
				throw new SaveDataException("Like not success");
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ObjectResponse("Like or dislike success", new HashMap<>() {
							{
								put("favoriteId", save.getId());
								put("statusFavorite", save.isFavorite());
							}
						}));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> editLikeBookByUer(Long favoriteId, boolean type) {
		Favorite favorite = favoriteRepository.getById(favoriteId);

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (Objects.isNull(favorite)) {
			throw new NotFoundException("favarite-ID khong ton tai");
		} else {
			if (user.getId() != favorite.getUser().getId()) {
				throw new NotFoundException("User khong phai nguoi da like or dislike");
			} else {
				favorite.setFavorite(type);
				Favorite update = favoriteRepository.save(favorite);

				if (Objects.isNull(update)) {
					throw new UpdateDataException("Update not success");
				} else {
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ObjectResponse("Update Like or dislike success", new HashMap<>() {
								{
									put("favoriteId", update.getId());
									put("type", type);
									put("statusFavorite", update.isFavorite());
								}
							}));
				}
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllBookFavoriteByUserId() {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (Objects.isNull(user)) {
			throw new NotFoundException("User khong ton tai");
		} else {
			List<Favorite> favorites = favoriteRepository.findFavoriteByUserId(user.getId());
			List<Book> books = new ArrayList<>();
			for (Favorite favorite : favorites) {
				Book book = new Book();
				book.setId(favorite.getBook().getId());
				book.setTitle(favorite.getBook().getTitle());
				book.setDescription(favorite.getBook().getDescription());
				book.setPrice(favorite.getBook().getPrice());
				book.setCopies(favorite.getBook().getCopies());
				book.setCopies_available(favorite.getBook().getCopies_available());
				book.setLanguage(favorite.getBook().getLanguage());
				book.setForUser(favorite.getBook().isForUser());
				book.setAuthors(favorite.getBook().getAuthors());
				book.setDepartments(favorite.getBook().getDepartments());
				book.setPublisher(favorite.getBook().getPublisher());
				books.add(book);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("list book like", new HashMap<>() {
				{
					put("userId", user.getId());
					put("litsFavorite", books);
					put("isFavorite", true);
				}
			}));
		}
	}

}
