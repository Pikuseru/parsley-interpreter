package parsley.pool;

import java.util.*;

public class Pool {
	private static final Stack pools = new Stack();

	private String name;
	private Stack objects;
	private PoolableFactory factory;
	private int creations, releases, recycles;
	
	public Pool(PoolableFactory factory, String name) {
		objects = new Stack();
		this.factory = factory;
		this.name = new String(name);
		pools.push(this);
	}
	
	public synchronized void put(Poolable object) throws IllegalArgumentException {
		if (! objects.contains(object)) {
			objects.push(object);
			object.onPooled();
			releases++;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public synchronized Poolable get() {
		Poolable object;
		
		if (objects.isEmpty()) {
			object = factory.newPoolable();
			object.setPool(this);
			creations++;
		}  else {
			object = (Poolable) objects.pop();
			recycles++;
		}
		
		return object;
	}
	
	public synchronized void clear() {
		objects.clear();
	}
	
	public String toString() {
		return new String(name + " " + objects.size() + " items, " + creations + " creations, " + recycles + " recycles, " + releases + " releases");
	}
	
	public static Enumeration pools() {
		return pools.elements();
	}
	
	public static void clearPools() {
		for (Enumeration e = pools() ; e.hasMoreElements() ; ) {
			Pool next = (Pool) e.nextElement();
			next.clear();
		}
	}
}