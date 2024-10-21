package the.coyote.produto.exception;

public class NotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFound(String message) {
		super(message);
	}

}
