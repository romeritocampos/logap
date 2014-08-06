package org.datagenerator.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cassandradb.CassandraModel;

public class Car implements CassandraModel {

	private int id = 0;
	private String name = "";
	
	public Car() {
		
	}
	
	public Car (int id, String name) {
		this.id = id;
		this.name = name;		
	}
	
	public Map<String, String> toCassandra() {
		
		Map<String, String> data = new LinkedHashMap<String, String>();
		
		data.put("id", String.valueOf(this.id));
		data.put("name", "'"+this.name+"'");
		
		return data;
	}

	public String getColumnFamily() {		
		return this.getClass().getSimpleName().toLowerCase();
	}

}
