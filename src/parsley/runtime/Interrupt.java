package parsley.runtime;

import parsley.model.*;

public class Interrupt {
	protected Signature signature;
	protected Table table;
	
	public Interrupt(Signature signature, Table table) {
		this.signature = signature;
		this.table = table; 
	}
	
	public Signature signature() {
		return signature;
	}
	
	public Table table() {
		return table;
	}
}
