package com.lt.crs.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lt.crs.validation.LoginValidation;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public class DbUtils {
	
	private static Logger logger = Logger.getLogger(DbUtils.class);
	
	private static Connection connection = null;
	
	/**
	 * @return
	 */
	public Connection createConnection() {

            try {
            	Properties prop = new Properties();
                InputStream inputStream = DbUtils.class.getClassLoader().getResourceAsStream("./config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
            	logger.error("Error generated"+e.getMessage());
            } catch (SQLException e) {
            	logger.error("Error generated"+e.getMessage());
            } catch (FileNotFoundException e) {
            	logger.error("Error generated"+e.getMessage());
            } catch (IOException e) {
            	logger.error("Error generated"+e.getMessage());
            }
            return connection;
        }

	
	/**
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
