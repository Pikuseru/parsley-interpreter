package parsley.model;

import java.util.*;

public class Signature {
	protected String string;
	
	public Signature(String name, Stack parameters) {
		if (null != parameters) {
			generateRepresentation(name, parameters.elements());
		} else {
			generateRepresentation(name, null);
		}
	}
	
	public Signature(String name) {
		this(name, null);
	}
	
	public Signature() {
		this("");
	}

	private void generateRepresentation(String name, Enumeration parameters) {
		StringBuffer buffer = new StringBuffer(name);
		while (parameters != null && parameters.hasMoreElements()) {
			buffer.append(parameters.nextElement());
		}
		string = buffer.toString();
	}
	
	public int hashCode() {
		return string.hashCode();
	}
	
	public boolean equals(Object object) {
		if (object instanceof Signature) {
			return object.hashCode() == hashCode();
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "Signature:\n" + string + "\n";
	}
}