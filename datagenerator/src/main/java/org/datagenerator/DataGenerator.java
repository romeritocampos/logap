package org.datagenerator;

import java.lang.reflect.Method;

import org.cassandradb.CassandraConnection;
import org.cassandradb.CassandraModel;
import org.cassandradb.CassandraTable;
import org.datagenerator.bean.Car;

public class DataGenerator {

	private CassandraConnection connection;	
	
	public DataGenerator(String contactPoint, String keyspace) {
		
		connection = new CassandraConnection(contactPoint);
		connection.connect(keyspace);	
	}
	
	public <T extends CassandraModel> void insert (Class<T> type, CassandraModel model) {
		
		connection.insert(type.getSimpleName(), model.toCassandra());
		
	}
	
	public <T extends CassandraModel> void select (Class<T> model) {
		
		Class<T> cm = model;				
		CassandraTable table = null;
		try {
			Method m = cm.getMethod("getColumnFamily");	
			table = connection.select(m.invoke(cm.newInstance()).toString());
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		
		System.out.println("Número de Linhas: " + table.getLines());
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		DataGenerator dg = new DataGenerator("127.0.0.1", "logap");
		
		dg.select(Car.class);
		
	}
	
	
}
