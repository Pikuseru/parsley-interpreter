package parsley.commands.core;

import parsley.commands.Command;
import parsley.commands.CommandFactory;
import parsley.exceptions.*;
import parsley.lexing.*;

import java.util.*;

public class CoreCommandFactory implements CommandFactory {
	private Hashtable commands;
	
	public CoreCommandFactory() {
		commands = new Hashtable();
		
		commands.put("push", new PushCommand());
		commands.put("save", new SaveCommand());
		commands.put("echo", new EchoCommand());
		commands.put("done", new DoneCommand());
		commands.put("proc", new ProcCommand());
		commands.put("call", new CallCommand());
		commands.put("labl", new LablCommand());
		commands.put("jump", new JumpCommand());
		commands.put("jint", new JintCommand());
		commands.put("halt", new HaltCommand());
		
		commands.put("gush", new GushCommand());
		commands.put("gave", new GaveCommand());
		
		commands.put("add", new AddCommand());
		commands.put("sub", new SubCommand());
		commands.put("dup", new DupCommand());
		commands.put("neg", new NegCommand());
		commands.put("mul", new MulCommand());
		commands.put("div", new DivCommand());
		commands.put("mod", new ModCommand());
		
		commands.put("eq", new EqCommand());
	}

	public Command createCommand(String commandString, Tokens tokens) throws UnknownCommandException {
		if (commands.containsKey(commandString)) {
			return ((Command) commands.get(commandString)).initWithTokens(tokens);
		} else {
			throw new UnknownCommandException(commandString);
		}
	}
}