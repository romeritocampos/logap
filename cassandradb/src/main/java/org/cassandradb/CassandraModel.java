package org.cassandradb;

import java.util.Map;

public interface CassandraModel {
	
	Map<String, String> toCassandra ();
	
}
