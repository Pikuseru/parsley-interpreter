package parsley.values;

import parsley.exceptions.*;

public abstract class Value {
	public abstract void setWithValue(Value other) throws ValueCastException;
	public abstract Value copyValue();
}
