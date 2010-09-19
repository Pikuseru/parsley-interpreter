package parsley.commands.core;

import parsley.runtime.*;

public class GaveCommand extends SaveCommand {
	public void execute(Context context) throws Exception {
		context.setGlobalValueForSymbol(symbol, context.popOperandValue());
	}
}
