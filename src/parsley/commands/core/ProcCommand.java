package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.model.*;
import parsley.runtime.*;
import java.util.*;

public class ProcCommand extends Command {
	protected String name;
	protected Stack parameters;
	
	public ProcCommand(Tokens tokens) {
		this();
		initWithTokens(tokens);
	}
	
	public ProcCommand() {
		parameters = new Stack();
	}
	
	public Command initWithTokens(Tokens tokens) {
		parameters.clear();
		setName(tokens.nextToken());
		setParameters(tokens);
		return this;
	}
	
	protected void setName(String token) throws IllegalArgumentException {
		if (isName(token)) {
			name = new String(token);
		} else {
			throw new IllegalArgumentException(token);
		}
	}
	
	protected void setParameters(Tokens tokens) throws IllegalArgumentException {
		while (tokens.hasMoreTokens()) {
			setParameter(tokens.nextToken());
		}
	}
	
	protected void setParameter(String token) throws IllegalArgumentException {
		if (isParameter(token)) {
			parameters.push(new Parameter(token));
		} else {
			throw new IllegalArgumentException(token);
		}
	}
	
	public Signature signature() {
		return new Signature(name, parameters);
	}
	
	public void execute(Context context) throws Exception {
	}
}
