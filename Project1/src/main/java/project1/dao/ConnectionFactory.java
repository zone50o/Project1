package project1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static String url = "jdbc:postgresql://35.223.59.115/DBProject1";
	public static String username = "postgres";
	public static String password = "p4ssw0rd";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
}
