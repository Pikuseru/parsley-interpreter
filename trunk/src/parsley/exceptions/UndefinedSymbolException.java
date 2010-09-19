package parsley.exceptions;

public class UndefinedSymbolException extends RuntimeException {
	private static final long serialVersionUID = -430133218015516088L;

	public UndefinedSymbolException() {
		super("undefined symbol");
	}

}
