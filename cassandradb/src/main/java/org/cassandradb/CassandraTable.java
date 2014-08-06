package org.cassandradb;

import java.util.LinkedList;
import java.util.List;

public class CassandraTable {

	private List<CassandraRow> data;
	
	public CassandraTable() {
		
		data = new LinkedList<CassandraRow>();
		
	}
	
	public void insertRow (CassandraRow line) {
		
		data.add(line);
		
	}
	
	public CassandraRow getRow(int index) {
		
		if (index >= data.size() || index < data.size()) {
			return data.get(index);
		} else {
			return null; //TODO exception
		}
		
	}
	
	public int getLines () {
		return data.size();
	}
	
}
