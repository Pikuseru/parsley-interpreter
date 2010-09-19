package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;
import parsley.runtime.*;

public class JumpCommand extends Command {
	protected String label;
	
	public JumpCommand(Tokens tokens) {
		initWithTokens(tokens);
	}
	
	public JumpCommand() {
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
	
	public void execute(Context context) throws Exception {
		context.jumpToLabel(label);
	}
}
