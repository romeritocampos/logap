package org.cassandradb;

import java.util.Map;

public interface CassandraModel {
	
	String getColumnFamily ();
	
	Map<String, String> toCassandra ();
	
}
