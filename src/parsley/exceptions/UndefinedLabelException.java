package parsley.exceptions;

public class UndefinedLabelException extends RuntimeException {
	private static final long serialVersionUID = 367316605183995486L;

	public UndefinedLabelException() {
		super("undefined label");
	}
}
