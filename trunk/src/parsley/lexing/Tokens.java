package parsley.lexing;

public class Tokens {
	protected String string;
	protected String delimiters;
	protected int start;
	protected int end;
	
	public Tokens(String string) {
		this(string, " \t");
	}
	
	public Tokens(String string, String delimiters) {
		setString(string);
		this.delimiters = new String(delimiters);
	}
	
	public void setString(String string) {
		this.string = new String(string);
		start = end = 0;
	}
	
	public boolean hasMoreTokens() {
		for (int i = end ; i < string.length() ; i++) {
			if (! isDelimiter(string.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	public String nextToken() {
		if (didFindNextToken()) {
			return string.substring(start, end);
		} else {
			return null;
		}
	}
	
	protected boolean didFindNextToken() {
		try {
			start = end;
			
			while (start < string.length() && isDelimiter(string.charAt(start))) {
				start++;
			}
			
			end = start;
			
			while (end < string.length() && ! isDelimiter(string.charAt(end))) {
					end++;
			}
			
			return start < end;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	protected boolean isDelimiter(char character) {
		return -1 != delimiters.indexOf(character);
	}
}
