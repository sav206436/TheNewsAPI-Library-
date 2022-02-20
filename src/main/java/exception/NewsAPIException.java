package exception;

//κλαση για custom exception error handling
public class NewsAPIException extends Exception {
	public NewsAPIException() {
		super();
	}

	public NewsAPIException(String message) {
		super(message);
	}

	public NewsAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsAPIException(Throwable cause) {
		super(cause);
	}

	protected NewsAPIException(String message, Throwable cause, boolean enableSuppresion, boolean writableStackTrace) {
		super(message, cause, enableSuppresion, writableStackTrace);
	}

}
