package parsley.commands.core;

import parsley.runtime.*;
import parsley.values.*;

public class EqCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		Value one = context.popOperandValue();
		Value two = context.popOperandValue();
		context.pushOperandValue(new BooleanValue(one.equals(two)));
	}
}