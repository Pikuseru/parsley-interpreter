package parsley.commands.core;

import parsley.runtime.*;

public class DoneCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		context.onProcedureDone();
	}
}