package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.runtime.*;
import parsley.values.*;

public class PushCommand extends Command {
	protected Symbol symbol;
	protected Value value;
	
	public PushCommand(Tokens tokens) {
		initWithTokens(tokens);
	}
	
	public PushCommand() {
	}
	
	public Command initWithTokens(Tokens tokens) {
		symbol = null; value = null;
		String token = tokens.nextToken();
		if (isSymbol(token)) {
			symbol = symbolForToken(token);
		} else if (isNumber(token)) {
			value = numberValueForToken(token);
		} else if (isString(token)) {
			value = stringValueForToken(token);
		} else if (isBoolean(token)) {
			value = booleanValueForToken(token);
		} else {
			throw new IllegalArgumentException(token);
		}		
		return this;
	}
	
	public void execute(Context context) throws Exception {
		Value valueToPush = null;
		if (symbol != null) {
			valueToPush = context.localValueForSymbol(symbol);
		} else {
			valueToPush = value;
		}
		context.pushOperandValue(valueToPush);
	}
}
