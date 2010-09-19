package parsley.commands.core;

import parsley.runtime.*;
import parsley.values.*;

public class NegCommand extends TokenlessCommand {
	public void execute(Context context) throws Exception {
		NumberValue value = (NumberValue) context.popOperandValue();
		context.pushOperandValue(new NumberValue(value.value() * -1));
	}
}