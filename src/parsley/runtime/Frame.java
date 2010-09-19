package parsley.runtime;

import parsley.model.*;
import parsley.exceptions.*;
import parsley.lexing.*;
import parsley.pool.*;

public class Frame extends Poolable {
	protected Procedure procedure;
	protected Table table;
	protected Scanner scanner;
	protected Context context;
	protected boolean running;
	
	private Frame() {
	}
	
	public Frame initWithProcedureTableContext(Procedure procedure, Table table, Context context) {
		this.procedure = procedure;
		this.table = table;
		this.context = context;
		running = true;		
		scanner = procedure.scanner().copy();
		scanner.resetCurrentLine();
		return this;
	}
	
	public Table symbolTable() {
		return table;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void execute() throws Exception {
		if (scanner.hasMoreLines()) {
			context.executeCommandFromTokens(scanner.nextLineAsTokens());
		} else {
			running = false;
		}
	}
	
	public void jumpToLabel(String label) throws UndefinedLabelException {
		scanner.setCurrentLine(procedure.lineForLabel(label));
	}
	
	protected void onPooled() {
		table.release();
		table = null;
		scanner.release();
		scanner = null;		
	}
	
	public static Frame newFrame() {
		return (Frame) pool.get();
	}
	
	private static final Pool pool = new Pool(new Factory(), "Frames");
	private static class Factory implements PoolableFactory {
		public Poolable newPoolable() {
			return new Frame();
		}
	}	
}
