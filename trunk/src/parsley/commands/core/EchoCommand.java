package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.runtime.*;
import parsley.values.*;

public class EchoCommand extends Command {
	protected String token;
	
	public EchoCommand(Tokens tokens) {
		initWithTokens(tokens);
	}
	
	public EchoCommand() {
	}
	
	public Command initWithTokens(Tokens tokens) {
		if (tokens.hasMoreTokens()) {
			token = tokens.nextToken();
		} else {
			token = null;
		}
		return this;
	}
	
	public void execute(Context context) throws Exception {
		Value value;
		
		if (token == null) {
			value = context.peekOperandValue();
		} else if (isSymbol(token)) {
			value = context.localValueForSymbol(new Symbol(token));
		} else if (isString(token)) {
			value = new StringValue(token);
		} else if (isNumber(token)) {
			value = new NumberValue(token);
		} else {
			throw new IllegalArgumentException(token);
		}
		
		System.out.println(value);
	}
}