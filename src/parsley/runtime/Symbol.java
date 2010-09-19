package parsley.runtime;

public class Symbol {
	protected String string;
	
	public Symbol(String string) {
		this.string = string;
	}
	
	public int hashCode() {
		return string.hashCode();
	}
	
	public boolean equals(Object object) {
		if (object instanceof Symbol) {
			return ((Symbol) object).hashCode() == hashCode();
		} else {
			return false;
		}
	}
	
	public String toString() {
		return new String(string);
	}
}
