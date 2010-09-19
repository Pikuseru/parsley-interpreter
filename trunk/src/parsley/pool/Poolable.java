package parsley.pool;

public abstract class Poolable {
	private Pool pool;
	
	protected void setPool(Pool pool) {
		this.pool = pool;
	}
	
	protected void onPooled() {
	}
	
	public void release() {
		pool.put(this);
	}
}