package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class DBConnect {
	
	private static String jdbcURL = "jdbc:mysql://localhost/countries?user=root" ;
	private static DataSource ds;
	
	public static Connection getConnection() {
		if(ds==null){
			//creo il datasource
			try {
				ds = DataSources.pooledDataSource(DataSources.unpooledDataSource(jdbcURL));//da un unpooled a un pooled
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1); //Esco dal programma perchè non posso fare più nulla
			} 
		}
		
		try {
			Connection c = ds.getConnection() ; //quando acquisisco la connessione dal pool, lui sa già l'URL quindi non serve passarglielo
			return c ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

}
