package parsley.runtime;

import java.util.*;

public class Interrupts {
	private Vector queue;
	
	public Interrupts() {
		queue = new Vector();
	}
	
	public synchronized void post(Interrupt intertupt) {
		queue.addElement(intertupt);
	}
	
	public synchronized Interrupt take() {
		if (queue.isEmpty()) {
			return null;
		} else {
			Interrupt result = (Interrupt) queue.firstElement();
			queue.removeElementAt(0);
			return result;
		}
	}
}
