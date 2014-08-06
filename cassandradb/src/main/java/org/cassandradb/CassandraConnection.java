package org.cassandradb;

import java.util.Map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnection {

	private Cluster cluster;
	private Session session;
	
	public CassandraConnection(String contactPoint) {
		
		cluster = Cluster.builder().addContactPoint(contactPoint).build();
		
	}
	
	public void connect (String keyspace) {
		
		session = cluster.connect(keyspace);		
		
	}
	
	public void close () {
		
	}

	public void insert (String columnName, Map<String, String> data) {
		
		
	}
	
	public Map<String, String> select (String columnName) {
		
		
		return null;
	}
	
}
