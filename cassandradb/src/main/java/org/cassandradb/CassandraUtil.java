package org.cassandradb;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Row;

public class CassandraUtil {

	public static String getRowDataAsString (int index, Row row, DataType dt) {
		
		String str = "";
		
		switch (dt.getName()) {
		case INT:
			str = String.valueOf(row.getInt(index));
			break;
		case VARCHAR:
			str = row.getString(index);
			break;
		case TIMESTAMP:
			str = row.getDate(index).toString();
			break;
		default:
			break;
		}
		
		return str;
		
	}
	
}
