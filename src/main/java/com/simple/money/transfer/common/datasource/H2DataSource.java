package com.simple.money.transfer.common.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.money.transfer.common.ApplicationProperties;

public class H2DataSource 
{
	
	private static final String DB_DRIVER = ApplicationProperties.getStringProperty("h2.driver");
	private static final String DB_CONNECTION_URL = ApplicationProperties.getStringProperty("h2.connection.url");
	private static final String DB_USER = ApplicationProperties.getStringProperty("h2.user");
	private static final String DB_PASSWORD = ApplicationProperties.getStringProperty("h2.password");
	
	
	private static Logger log = LoggerFactory.getLogger(H2DataSource.class);
	private static Connection connection;
	
	static 
	{
		try 
		{
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) 
		{
			log.error("Unable to Load Database Driver" , e);
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASSWORD);
		connection.setAutoCommit(false);
		return connection;
	}
	
	public static void quietlyClose(ResultSet rs) {
        if (rs != null) {
            try {
            	rs.close();
            } catch (SQLException e) {
                log.error("Unexpected exception while closing Result Set", e);
            }
        }
    }
	
	public void initialize() 
	{
		try( Connection conn = H2DataSource.getConnection() ) 
		{
			log.info("Creating Schema");
			RunScript.execute( conn, new FileReader("src/main/resources/db_schema/schema.sql") );
			log.info("Creating Sample Data Rows");
			RunScript.execute( conn, new FileReader("src/main/resources/db_schema/init_data.sql") );
			conn.commit();
		} 
		catch (FileNotFoundException e)  
		{
			log.error( "Cannot load Schema or Data file(s)" , e);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			log.error( "Exception While Executing SQL " , e);
			throw new RuntimeException(e);
		}
		
		
	}
	

}
