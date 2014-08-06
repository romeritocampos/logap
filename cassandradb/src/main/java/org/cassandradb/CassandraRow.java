package org.cassandradb;

import java.util.Iterator;
import java.util.List;

public class CassandraRow {

	private List<String> row;
	
	public CassandraRow(List<String> row) {
		this.row = row;
	}
	
	public String getColumn (int index) {
		
		if (index >= row.size() || index < row.size()) {
			return row.get(index);
		} else {
			return "";
		}
		
	}
	
	public int size () {
		return row.size();		
	}
	
	public Iterator<String> iterator () {
		return row.iterator();
	}
	
	@Override
	public String toString () {
		
		String str = row.toString();		
		return "("+str.substring(1,str.length()-1)+")";		
	}
}
