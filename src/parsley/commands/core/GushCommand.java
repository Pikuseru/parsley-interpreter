package parsley.commands.core;

import parsley.runtime.*;
import parsley.values.*;

public class GushCommand extends PushCommand {
	public void execute(Context context) throws Exception {
		Value valueToPush = null;
		if (symbol != null) {
			valueToPush = context.globalValueForSymbol(symbol);
		} else {
			valueToPush = value;
		}
		context.pushOperandValue(valueToPush);
	}
}
