package parsley.runtime;

import parsley.exceptions.*;
import parsley.values.*;
import parsley.lexing.*;
import parsley.model.*;

import java.util.*;

public class Context implements Runnable {
	protected Table globals;
	protected Engine engine;
	protected Program program;	
	protected Procedure procedure;
	protected Interrupts interrupts;
	protected Stack frames;
	protected Stack values;
	protected boolean halted;
	
	public Context(Engine engine, Program program, Interrupts interrupts) {
		this(engine, program, program.procedureForMain(), interrupts);
	}
	
	public Context(Engine engine, Program program, Procedure procedure, Interrupts interrupts) {
		this.engine = engine;
		this.program = program;
		this.procedure = procedure;
		this.interrupts = interrupts;
		frames = new Stack();
		values = new Stack();
		globals = Table.newTable();
		halted = false;
	}
	
	public void setGlobalValueForSymbol(Symbol symbol, Value value) {
		globals.save(symbol, value);
	}
	
	public Value globalValueForSymbol(Symbol symbol) throws UndefinedSymbolException {
		return globals.load(symbol);
	}
	
	public void setLocalValueForSymbol(Symbol symbol, Value value) {
		topFrame().symbolTable().save(symbol, value);
	}
	
	public Value localValueForSymbol(Symbol symbol) throws UndefinedSymbolException {
		return topFrame().symbolTable().load(symbol);
	}

	public Value peekOperandValue() {
		return (Value) values.peek();
	}
	
	public Value popOperandValue() {
		return (Value) values.pop();
	}
	
	public void pushOperandValue(Value value) {
		values.push(value);
	}
	
	public void onProcedureCall(Signature signature, Table table) { 
		frames.push(Frame.newFrame().initWithProcedureTableContext(program.procedureForSignature(signature), table, this));
	}
	
	public void onProcedureDone() {
		if (! frames.isEmpty()) {
			popTopFrame();
		}
	}
	
	protected void popTopFrame() {
		Frame frame = (Frame) frames.pop();
		frame.release();		
	}
	
	protected Frame topFrame() {
		return (Frame) frames.peek();
	}
	
	protected Frame bottomFrame() {
		return (Frame) frames.firstElement();
	}
	
	private boolean isRunning() {
		if (halted || frames.isEmpty()) {
			return false;
		} else {
			return bottomFrame().isRunning();
		}
	}
	
	private void executeTopFrame() throws Exception {
		topFrame().execute();
	}
	
	private void processInterrupts() {
		if (interrupts != null) {
			Interrupt interrupt = interrupts.take();
			if (interrupt != null) {
				onProcedureCall(interrupt.signature(), interrupt.table());
			}
		}
	}
	
	public void run() {
		onProcedureCall(procedure.signature(), Table.newTable());
		try {
			while (isRunning()) {
				executeTopFrame();
				processInterrupts();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		onProcedureDone();
		halt();
	}
	
	public void halt() {
		halted = true;
		globals.release();
		globals = null;
	}
	
	public void executeCommandFromTokens(Tokens tokens) throws Exception {
		engine.createCommand(tokens).execute(this);
	}
	
	public void jumpToLabel(String label) throws UndefinedLabelException {
		topFrame().jumpToLabel(label);
	}
}