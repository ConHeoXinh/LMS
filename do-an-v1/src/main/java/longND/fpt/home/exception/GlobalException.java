package longND.fpt.home.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorDetails> handleAPIException(APIException exception, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), req.getDescription(false),
				LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> handlerNotFoundException(NotFoundException e, WebRequest req) {
		ErrorDetails error = new ErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorDetails> handlerAuthException(AuthException e, WebRequest req) {
		ErrorDetails error = new ErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@ExceptionHandler(BadRequestAlertException.class)
	public ResponseEntity<ErrorDetails> handlerBadRequestAlertException(BadRequestAlertException e, WebRequest req) {
		ErrorDetails error = new ErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UpdateDataException.class)
	public ResponseEntity<ErrorDetails> handlerUpdateDataException(UpdateDataException ex, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(SaveDataException.class)
	public ResponseEntity<ErrorDetails> handlerSaveDataException(SaveDataException ex, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
	}

	// global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception,
			WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), webRequest.getDescription(false),
				LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
}
