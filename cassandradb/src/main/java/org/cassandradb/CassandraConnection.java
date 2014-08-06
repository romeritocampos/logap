package org.cassandradb;

import java.util.LinkedHashMap;
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
		
		session.close ();
		cluster.close();
		
	}

	public void insert (String columnName, Map<String, String> data) {
		
		String insert = "INSERT INTO ";
		String columns = " (";
		String info = " (";
		
		for (String key : data.keySet()) {
			columns += key + ",";
			info += data.get(key) + ",";	
		}
		
		columns = columns.subSequence(0, columns.length()-1) + ") ";
		info = info.substring(0, info.length()-1) + ");";
		
		insert += columnName + columns + " VALUES " + info;
		
		session.execute(insert);
		
	}
	
	public Map<String, String> select (String columnName) {
		
		
		
		return null;
	}
	
	public static void main (String[] args) {
		
		
		CassandraConnection cc = new CassandraConnection("127.0.0.1");
		
		cc.connect("logap");
		
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("id","2");
		data.put("name", "'fusca'");
		
		cc.insert("car", data);
		
		
		
	}
	
}
