package parsley.utility;

public class Range {
	protected int from, to;
	
	public Range(int from, int to) {
		setFrom(from);
		setTo(to);
	}
	
	public void setFrom(int from) {
		this.from = from;
	}
	
	public void setTo(int to) {
		this.to = to;
	}
	
	public int from() {
		return from;
	}
	
	public int to() {
		return to;
	}
}
