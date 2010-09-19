package parsley.model;

import parsley.commands.*;
import parsley.commands.core.DoneCommand;
import parsley.commands.core.LablCommand;
import parsley.commands.core.ProcCommand;
import parsley.exceptions.*;
import parsley.lexing.*;
import parsley.runtime.Engine;
import parsley.utility.*;

import java.util.Enumeration;
import java.util.Hashtable;

public class Program {
	protected Engine engine;
	protected Hashtable procedures;
	
	public Program(Engine engine, Scanner scanner) throws UnknownCommandException {
		this.engine = engine;
		procedures = new Hashtable();
		scanProceduresWithScanner(scanner);
	}
	
	public void addProcedure(Procedure procedure) {
		if (this != procedure.program()) {
			procedure.setProgram(this);
		}
		procedures.put(procedure.signature(), procedure);
	}
	
	public Procedure procedureForSignature(Signature signature) {
		return (Procedure) procedures.get(signature);
	}
	
	public Procedure procedureForMain() {
		return procedureForSignature(engine.mainSignature());
	}
	
	private void scanProceduresWithScanner(Scanner scanner) throws UnknownCommandException {
		Signature signature = null;
		Command command = null;
		Labels labels = null;
		int procStart = 0, current = 0;
		
		while (scanner.hasMoreLines()) {
			current = scanner.currentLine();
			command = engine.createCommand(scanner.nextLineAsTokens());
			
			if (command instanceof ProcCommand) {
				if (null == signature) {
					signature = ((ProcCommand) command).signature();
					procStart = current;
					labels = new Labels();
				} else {
					throw new IllegalStateException();
				}
			}
			
			if (command instanceof DoneCommand) {
				if (null != signature) {
					addProcedure(new Procedure(this, signature, scanner.copyWithRange(new Range(procStart, current)), labels));
					signature = null;
					labels = null;
				} else {
					throw new IllegalStateException();
				}
			}
			
			if (command instanceof LablCommand) {
				labels.setLineForLabel(((LablCommand) command).label(), current - procStart);
			}
		}
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Program:\n");
		for (Enumeration e = procedures.elements() ; e.hasMoreElements() ; ) {
			buffer.append(e.nextElement());
		}
		return buffer.toString();
	}
}
