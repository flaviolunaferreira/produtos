package the.coyote.produto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import jakarta.servlet.ServletRequest;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NotFound.class)
	public ResponseEntity<StandardError> notFoundException(NotFound e, ServletRequest request) {
		StandardError error = new StandardError (
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(IntegratyViolation.class)
	public ResponseEntity<StandardError> integratyViolationException(IntegratyViolation e, ServletRequest request) {
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}

	@ExceptionHandler(DuplicateValue.class)
	public ResponseEntity<StandardError> duplicateValueExcepition(DuplicateValue e, ServletRequest request) {
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.CONFLICT.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}

	@ExceptionHandler(InternalServerErro.class)
	public ResponseEntity<StandardError> internalServerErrorExcepition(InternalServerErro e, ServletRequest request) {
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}
}
