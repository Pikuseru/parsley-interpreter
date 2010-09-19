package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.runtime.*;

public class SaveCommand extends Command {
	protected Symbol symbol;
	
	public SaveCommand(Tokens tokens) {
		initWithTokens(tokens);
	}
	
	public SaveCommand() {
	}
	
	public Command initWithTokens(Tokens tokens) {
		String token = tokens.nextToken();
		if (isSymbol(token)) {
			symbol = symbolForToken(token);
		} else {
			throw new IllegalArgumentException(token);
		}		
		return this;
	}
	
	public void execute(Context context) throws Exception {
		context.setLocalValueForSymbol(symbol, context.popOperandValue());
	}
}
