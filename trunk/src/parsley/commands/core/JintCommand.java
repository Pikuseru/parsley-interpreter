package parsley.commands.core;

import parsley.lexing.*;
import parsley.runtime.*;
import parsley.values.*;

public class JintCommand extends JumpCommand {
	public JintCommand(Tokens tokens) {
		super(tokens);
	}
	
	public JintCommand() {
		super();
	}
	
	public void execute(Context context) throws Exception {
		BooleanValue bool = (BooleanValue) context.popOperandValue();
		if (! bool.value()) {
			context.jumpToLabel(label);
		}
	}
}
