package the.coyote.produto.exception;

public class InternalServerErro extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public InternalServerErro(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerErro(String message) {
		super(message);
	}

}
