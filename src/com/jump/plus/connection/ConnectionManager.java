package com.jump.plus.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static Connection connection; // will be null at moment

	private static void makeConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Register Driver");

			Properties props = new Properties();
			
			props.load( new FileInputStream("resources/config.properties") );
			
			// use MACURL for mac WINURL for win 
			String url = props.getProperty("URL");
			String username = props.getProperty("USER_NAME");
			String password = props.getProperty("PASSWORD");

			connection = DriverManager.getConnection(url, username, password);
//			System.out.println("Connected.");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		if (connection == null) {
			makeConnection();
		}

		return connection;
	}
}
