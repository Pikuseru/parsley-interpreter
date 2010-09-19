package parsley.commands.core;

import parsley.runtime.*;

public class HaltCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		context.halt();
	}
}