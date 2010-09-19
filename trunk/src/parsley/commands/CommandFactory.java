package parsley.commands;

import parsley.exceptions.UnknownCommandException;
import parsley.lexing.Tokens;

public interface CommandFactory {
	public Command createCommand(String commandString, Tokens tokens) throws UnknownCommandException;
}
