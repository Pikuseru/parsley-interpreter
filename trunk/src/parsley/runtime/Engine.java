package parsley.runtime;

import parsley.commands.*;
import parsley.commands.core.*;
import parsley.exceptions.*;
import parsley.lexing.*;
import parsley.model.*;

import java.util.*;

public class Engine {
	protected Stack commandFactories;

	public Engine() {
		commandFactories = new Stack();
	}
	
	public Command createCommand(Tokens tokens) throws UnknownCommandException {
		String commandString = tokens.nextToken();
		int i = commandFactories.size() - 1;
		while (0 <= i) {
			try {
				return ((CommandFactory) commandFactories.elementAt(i)).createCommand(commandString, tokens);
			} catch (UnknownCommandException e) {
				i--;
			}
		}
		throw new UnknownCommandException(commandString);
	}
	
	public void registerCoreCommands() {
		registerCommandFactory(new CoreCommandFactory());
	}
	
	public void registerCommandFactory(CommandFactory factory) {
		commandFactories.push(factory);
	}
	
	public Signature mainSignature() {
		return new Signature("main");
	}
}