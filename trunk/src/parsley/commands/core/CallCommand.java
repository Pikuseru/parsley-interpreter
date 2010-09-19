package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.model.*;
import parsley.runtime.*;
import parsley.values.*;
import java.util.*;

public class CallCommand extends Command {
	protected String name;
	protected Stack parameters;
	protected Hashtable arguments;
	
	public CallCommand(Tokens tokens) {
		this();
		initWithTokens(tokens);
	}
	
	public CallCommand() {
		parameters = new Stack();
		arguments = new Hashtable();
	}
	
	public Command initWithTokens(Tokens tokens) {
		parameters.clear();
		arguments.clear();
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
	
	protected void setParameters(Tokens tokens) {
		String previous = null, current = null;
		while (tokens.hasMoreTokens()) {
			current = tokens.nextToken();
			if (isParameter(current)) {
				setParameter(current);
			} else if (isParameter(previous)) {
				setArgument(previous, current);
			} else {
				throw new IllegalArgumentException(current);
			}
			previous = current;
		}
	}
	
	protected void setParameter(String token) throws IllegalArgumentException {
		if (isParameter(token)) {
			parameters.push(new Parameter(token));
		} else {
			throw new IllegalArgumentException(token);
		}
	}
	
	protected void setArgument(String parameter, String argument) {
		arguments.put(new Parameter(parameter), argument);
	}
	
	private Value valueForToken(Context context, String token) {
		if (isSymbol(token)) {
			return context.localValueForSymbol(symbolForToken(token));
		} else if (isNumber(token)) {
			return numberValueForToken(token);
		} else if (isString(token)) {
			return stringValueForToken(token);
		} else if (isBoolean(token)) {
			return booleanValueForToken(token);
		} else {
			throw new IllegalStateException(token);
		}
	}
	
	public Signature signature() {
		return new Signature(name, parameters);
	}
	
	public void execute(Context context) throws Exception {
		Enumeration enumeration = arguments.keys();
		Table table = Table.newTable();
		
		while (enumeration.hasMoreElements()) {
			Parameter parameter = (Parameter) enumeration.nextElement();
			table.save(symbolForParameter(parameter), valueForToken(context, (String) arguments.get(parameter))); 
		}
		
		context.onProcedureCall(signature(), table);
	}
}
