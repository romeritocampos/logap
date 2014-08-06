package org.cassandradb;

import java.util.Calendar;
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
		
		System.out.println(insert);
		
		session.execute(insert);
		
	}
	
	public CassandraTable select (String columnName) {
		
		ResultSet rs = session.execute("SELECT * FROM " + columnName);
		ColumnDefinitions cd = rs.getColumnDefinitions();
		
		CassandraTable table = new CassandraTable();
		Iterator<Row> it = rs.iterator();
		 
		while (it.hasNext()) {
			
			Row row = it.next();			
			//lines.add(new LinkedList<String>());
			
			List<String> tmp_line = new LinkedList<String>();			
			for (int i=0; i < cd.size(); i++) {
				
				String info = CassandraUtil.getRowDataAsString(i, row, cd.getType(i));
				tmp_line.add(info);				
			}			
			table.insertRow(new CassandraRow(tmp_line));
			
		}
			
		return table;
	}
	
	public static void main (String[] args) {
		
		
		CassandraConnection cc = new CassandraConnection("127.0.0.1");
		
		cc.connect("logap");
		
		
		
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("id_carro","2");
		data.put("time", String.valueOf(Calendar.getInstance().getTime().getTime()) );
		data.put("speed", "150.5");	
		
		cc.insert("car_speed", data);
		
		
		
		CassandraTable table = cc.select("car_speed");
		
		for (int i=0; i < table.getLines(); i++) {
			
			CassandraRow row = table.getRow(i);
			for (int j=0; j < row.size(); j++) {
				System.out.print(row.getColumn(j));
			}
			System.out.println();
			
		}
		
	}
	
}
