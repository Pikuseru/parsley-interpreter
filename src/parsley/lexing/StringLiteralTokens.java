package parsley.lexing;

public class StringLiteralTokens extends Tokens {
	public StringLiteralTokens(String string, String delimiters) {
		super(string, delimiters);
	}
	
	public StringLiteralTokens(String string) {
		super(string);
	}

	public String nextToken() throws IllegalStateException {
		String token = super.nextToken();
		if (token.startsWith("\"") && ! token.endsWith("\"")) {
			StringBuffer sink = new StringBuffer(token);
			do {
				token = super.nextToken();
				if (token == null) {
					throw new IllegalStateException(string);
				}
				sink.append(' ');
				sink.append(token);
			} while (! token.endsWith("\""));
			token = sink.toString();
		}
		return token;
	}
}
