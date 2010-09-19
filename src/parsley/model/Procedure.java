package parsley.model;

import parsley.lexing.*;
import parsley.exceptions.*;

public class Procedure {
	protected Program program;
	protected Signature signature;
	protected Scanner scanner;
	protected Labels labels;
	
	public Procedure(Program program, Signature signature, Scanner scanner, Labels labels) {
		setProgram(program);
		setSignature(signature);
		setScanner(scanner);
		setLabels(labels);
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void setLabels(Labels labels) {
		this.labels = labels;
	}
	
	public Program program() {
		return program;
	}
	
	public Signature signature() {
		return signature;
	}
	
	public Scanner scanner() {
		return scanner;
	}
	
	public int lineForLabel(String label) throws UndefinedLabelException {
		return labels.lineForLabel(label);
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Procedure:\n");
		buffer.append(signature);
		buffer.append(scanner);
		return buffer.toString();
	}
}
