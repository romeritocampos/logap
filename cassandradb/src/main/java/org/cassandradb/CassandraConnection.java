package org.cassandradb;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
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
	
	public List<List<String>> select (String columnName) {
		
		ResultSet rs = session.execute("SELECT * FROM " + columnName);
		ColumnDefinitions cd = rs.getColumnDefinitions();
		
		List<List<String>> lines = new LinkedList<List<String>>();
		Iterator<Row> it = rs.iterator();
		
		int line=0; 
		while (it.hasNext()) {
			
			Row row = it.next();			
			lines.add(new LinkedList<String>());
			
			List tmp_line = lines.get(line);
			for (int i=0; i < cd.size(); i++) {
				
				String info = CassandraUtil.getRowDataAsString(i, row, cd.getType(i));
				tmp_line.add(info);				
			}
			
			++line;
			
		}
			
		return lines;
	}
	
	public static void main (String[] args) {
		
		
		CassandraConnection cc = new CassandraConnection("127.0.0.1");
		
		cc.connect("logap");
		
		/*
		
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("id","2");
		data.put("name", "'fusca'");
		
		cc.insert("car", data);
		*/
		
		
		List<List<String>> lines = cc.select("car");
		
		for (List<String> line : lines) {
			System.out.println(line);
		}
		
	}
	
}
