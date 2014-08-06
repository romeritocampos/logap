package org.datagenerator;

import java.lang.reflect.Method;

import org.cassandradb.CassandraConnection;
import org.cassandradb.CassandraModel;
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
		
		try {
			Method m = cm.getMethod("getColumnFamily");	
			connection.select(m.invoke(cm.newInstance()).toString()).size();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		DataGenerator dg = new DataGenerator("127.0.0.1", "logap");
		
		/*
		for (int i=100; i < 200; i++) {
			
			CassandraModel model = new Car(i, "car" );			
			dg.insert(Car.class, model);
			
		}
		*/
		dg.select(Car.class);
		
	}
	
	
}
