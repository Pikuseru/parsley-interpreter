package parsley.values;

import parsley.exceptions.ValueCastException;

public class StringValue extends Value {
	protected String value;
	
	public StringValue(String token) {
		value = unquotedString(token);
	}
	
	public StringValue(Value value) throws ValueCastException {
		setWithValue(value);
	}
	
	public String value() {
		return new String(value);
	}

	public void setWithValue(Value other) throws ValueCastException {
		if (other instanceof StringValue) {
			value = ((StringValue) other).value;
		} else if (other instanceof NumberValue) {
			value = String.valueOf(((NumberValue) other).value);
		} else if (other instanceof BooleanValue) {
			value = String.valueOf(((BooleanValue) other).value);
		} else {
			throw new ValueCastException();
		}
	}
	
	public String toString() {
		return "String:" + value;
	}
	
	private String unquotedString(String value) {
		return value.substring(1, value.length() - 1);
	}
	
	public Value copyValue() {
		return new StringValue(value);
	}
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof StringValue) {
			return hashCode() == object.hashCode();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return value.hashCode();
	}	
}