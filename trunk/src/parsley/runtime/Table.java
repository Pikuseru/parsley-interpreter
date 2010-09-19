package parsley.runtime;

import parsley.exceptions.*;
import parsley.values.*;
import parsley.pool.*;

import java.util.*;

public class Table extends Poolable {
	protected Hashtable table;
	
	private Table() {
		 table = new Hashtable();
	}
	
	public void save(Symbol symbol, Value value) {
		table.put(symbol, value);
	}
	
	public Value load(Symbol symbol) throws UndefinedSymbolException {
		if (table.containsKey(symbol)) {
			return (Value) table.get(symbol);
		} else {
			throw new UndefinedSymbolException();
		}
	}
	
	protected void onPooled() {
		table.clear();
	}
	
	public static Table newTable() {
		return (Table) pool.get();
	}
	
	private static final Pool pool = new Pool(new Factory(), "Tables");
	private static class Factory implements PoolableFactory {
		public Poolable newPoolable() {
			return new Table();
		}
	}
}