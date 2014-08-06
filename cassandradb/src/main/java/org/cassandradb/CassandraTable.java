package org.cassandradb;

import java.util.LinkedList;
import java.util.List;

public class CassandraTable {

	private List<List<String>> data;
	
	public CassandraTable() {
		
		data = new LinkedList<List<String>>();
		
	}
	
	public void insertRow (List<String> line) {
		
		data.add(line);
		
	}
	
	public List<String> getLine(int index) {
		
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
