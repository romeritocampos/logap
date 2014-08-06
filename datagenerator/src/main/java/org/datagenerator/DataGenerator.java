package org.datagenerator;

import org.cassandradb.CassandraConnection;

public class DataGenerator {

	private CassandraConnection connection;	
	private static int id = 0;
	
	public DataGenerator(String contactPoint) {
		
		connection = new CassandraConnection(contactPoint);
		
	}
	
	
	
}
