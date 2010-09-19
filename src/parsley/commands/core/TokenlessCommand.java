package parsley.commands.core;

import parsley.commands.Command;
import parsley.lexing.*;

public abstract class TokenlessCommand extends Command {
	public Command initWithTokens(Tokens tokens) {
		return this;
	}
}
