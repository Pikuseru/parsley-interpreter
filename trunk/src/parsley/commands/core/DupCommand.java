package parsley.commands.core;

import parsley.runtime.*;

public class DupCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		context.pushOperandValue(context.peekOperandValue().copyValue());
	}
}