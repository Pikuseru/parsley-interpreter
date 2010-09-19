package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.runtime.*;

public class LablCommand extends Command {
	protected String label;
	
	public LablCommand(Tokens tokens) {
		initWithTokens(tokens);
	}
	
	public LablCommand() {
	}
	
	public Command initWithTokens(Tokens tokens) {
		String token = tokens.nextToken();
		if (isName(token)) {
			label = token;
		} else {
			throw new IllegalArgumentException(token);
		}
		return this;
	}
	
	public String label() {
		return new String(label);
	}
	
	public void execute(Context context) throws Exception {
	}
}
