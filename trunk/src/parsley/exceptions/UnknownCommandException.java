package parsley.exceptions;

public class UnknownCommandException extends RuntimeException {
	private static final long serialVersionUID = 562156404621417663L;

	public UnknownCommandException(String commandString) {
		super("unknown command " + commandString);
	}
}
