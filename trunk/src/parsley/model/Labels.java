package parsley.model;

import parsley.exceptions.*;

import java.util.*;

public class Labels {
	private Hashtable labels;
	
	public Labels() {
		labels = new Hashtable();
	}
	
	public void setLineForLabel(String label, int line) {
		labels.put(label, new Integer(line));
	}
	
	public int lineForLabel(String label) throws UndefinedLabelException {
		if (labels.containsKey(label)) {
			return ((Integer) labels.get(label)).intValue();
		} else {
			throw new UndefinedLabelException();
		}
	}
}
