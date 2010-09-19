package parsley.lexing;

import parsley.utility.*;
import parsley.pool.*;

import java.util.*;
import java.io.*;

public class Scanner extends Poolable {
	protected Vector lines;
	protected int currentLine;
	
	private Scanner() {
	}
	
	public Scanner initWithString(String string) {
		initWithTokens(new Tokens(string, ","));
		return this;
	}
	
	public Scanner initWithTokens(Tokens tokens) {
		initWithVector(new Vector());
		readLines(tokens);
		return this;
	}

	public Scanner initWithScannerRange(Scanner scanner, Range range) {
		initWithVector(new Vector());
		readLines(scanner.lines, range);
		return this;
	}
	
	public Scanner initWithReader(Reader reader) throws IOException {
		initWithVector(new Vector());
		readLines(new BufferedReader(reader));
		return this;
	}
	
	public Scanner initWithVector(Vector lines) {
		this.lines = new Vector(lines);
		return this;
	}
	
	public Scanner initWithScanner(Scanner scanner) {
		initWithVector(scanner.lines);
		currentLine = scanner.currentLine;
		return this;
	}
	
	private void readLines(Tokens tokens) {
		while (tokens.hasMoreTokens()) {
			addLineWithFiltering(tokens.nextToken());
		}
	}
	
	private void readLines(BufferedReader reader) throws IOException {
		String line = null;
		while (null != (line = reader.readLine())) {
			addLineWithFiltering(line);
		}
	}
	
	private void readLines(Vector lines, Range range) {
		for (int i = 0 ; i < lines.size() ; i++) {
			if (range.from() <= i && i <= range.to()) {
				addLineWithFiltering((String) lines.elementAt(i));
			}
		}
	}
	
	public void addLineWithFiltering(String line) {
		if (line != null) {
			line = line.trim();
			if (0 < line.trim().length() && ! line.startsWith(";;;;")) {
				lines.add(line);
			}
		}
	}
	
	public Tokens nextLineAsTokens() {
		if (hasMoreLines()) {
			return new StringLiteralTokens((String) lines.get(currentLine++));
		} else {
			return null;
		}
	}
	
	public Scanner copy() {
		new_scanners++;
		return newScanner().initWithScanner(this);
	}
	
	public Scanner copyWithRange(Range range) {
		new_scanners++;
		return newScanner().initWithScannerRange(this, range);
	}
	
	public int totalLines() {
		return lines.size();
	}
	
	public boolean hasMoreLines() {
		return currentLine < lines.size();
	}
	
	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}
	
	public int currentLine() {
		return currentLine;
	}
	
	public void resetCurrentLine() {
		currentLine = 0;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer("Scanner:");
		buffer.append('\n');
		for (Enumeration e = lines.elements() ; e.hasMoreElements() ; ) {
			buffer.append(e.nextElement());
			buffer.append('\n');
		}
		return buffer.toString();
	}
	
	protected void onPooled() {
		lines = null;
		resetCurrentLine();
	}
	
	public static Scanner newScanner() {
		return (Scanner) pool.get();
	}
	
	private static final Pool pool = new Pool(new Factory(), "Scanners");
	private static class Factory implements PoolableFactory {
		public Poolable newPoolable() {
			return new Scanner();
		}
	}
	
	private static int new_scanners;
	public static int new_scanners() {
		return new_scanners;
	}
}
