package parsley.exceptions;

public class ValueCastException extends RuntimeException {
	private static final long serialVersionUID = 5035561700027422751L;

	public ValueCastException() {
		super("value cast exception");
	}
}
