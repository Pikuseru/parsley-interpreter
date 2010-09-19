package parsley.values;

import parsley.exceptions.ValueCastException;

public class NumberValue extends Value {
	protected int value;
	
	public NumberValue(String token) {
		value = Integer.parseInt(token);
	}
	
	public NumberValue(int value) {
		this.value = value;
	}
	
	public NumberValue(Integer value) {
		this(value.intValue());
	}
	
	public NumberValue(Value other) throws ValueCastException {
		setWithValue(other);
	}
	
	public int value() {
		return value;
	}
	
	public void setWithValue(Value other) throws ValueCastException {
		if (other instanceof NumberValue) {
			value = ((NumberValue) other).value;
		} else {
			throw new ValueCastException();
		}
	}
	
	public String toString() {
		return "Number:" + value;
	}
	
	public Value copyValue() {
		return new NumberValue(value);
	}
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof NumberValue) {
			return hashCode() == object.hashCode();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return String.valueOf(value).hashCode();
	}	
}