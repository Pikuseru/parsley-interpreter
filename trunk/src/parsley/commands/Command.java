package parsley.commands;

import parsley.runtime.*;
import parsley.values.*;
import parsley.lexing.*;

public abstract class Command {
	public abstract void execute(Context context) throws Exception;
	public abstract Command initWithTokens(Tokens tokens);
	
	protected boolean isBoolean(String token) {
		return "true".equalsIgnoreCase(token) || "false".equalsIgnoreCase(token);
	}
	
	protected boolean isNumber(String token) {
		try {
			Integer.parseInt(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	protected boolean isString(String token) {
		return ('"' == token.charAt(0)) && ('"' == token.charAt(token.length() - 1));
	}
	
	protected boolean isSymbol(String token) {
		return token.startsWith("#") && ! token.endsWith(":");
	}
	
	protected boolean isName(String token) {
		return ! isNumber(token) && ! isSymbol(token) && ! isString(token) && ! isParameter(token);
	}
	
	protected boolean isParameter(String token) {
		return token.startsWith("#") && token.endsWith(":");
	}
	
	protected Value booleanValueForToken(String token) {
		return new BooleanValue(token);
	}
	
	protected Value numberValueForToken(String token) {
		return new NumberValue(token);
	}
	
	protected Value stringValueForToken(String token) {
		return new StringValue(token);
	}
	
	protected Symbol symbolForToken(String token) {
		return new Symbol(token);
	}
	
	protected Symbol symbolForParameter(Parameter parameter) {
		String string = parameter.toString();
		return new Symbol(string.substring(0, string.length() - 1));
	}
}
