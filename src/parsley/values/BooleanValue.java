package parsley.values;

import parsley.exceptions.ValueCastException;

public class BooleanValue extends Value {
	protected boolean value;
	
	public BooleanValue(String token) {
		value = Boolean.parseBoolean(token);
	}
	
	public BooleanValue(int value) {
		this.value = (value != 0);
	}
	
	public BooleanValue(boolean value) {
		this.value = value;
	}
	
	public BooleanValue(Value other) throws ValueCastException {
		setWithValue(other);
	}
	
	public boolean value() {
		return value;
	}
	
	public void setWithValue(Value other) throws ValueCastException {
		if (other instanceof NumberValue) {
			value = (((NumberValue) other).value != 0);
		} else if (other instanceof BooleanValue) {
			value = ((BooleanValue) other).value;
		} else {
			throw new ValueCastException();
		}
	}
	
	public String toString() {
		return "Boolean:" + value;
	}
	
	public Value copyValue() {
		return new BooleanValue(value);
	}
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof BooleanValue) {
			return hashCode() == object.hashCode();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return String.valueOf(value).hashCode();
	}
}