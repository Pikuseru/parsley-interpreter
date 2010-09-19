package parsley.commands.core;

import parsley.runtime.*;
import parsley.values.*;

public class ModCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		NumberValue one = (NumberValue) context.popOperandValue();
		NumberValue two = (NumberValue) context.popOperandValue();
		Value result = new NumberValue(one.value() % two.value());
		context.pushOperandValue(result);
	}
}