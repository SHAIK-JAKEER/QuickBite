package com.tap.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private final static String URL="jdbc:mysql://localhost:3306/tap";
	private final static String USERNAME="root";
	private final static String PASSWORD="root";

	public static Connection connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				return DriverManager.getConnection(URL,USERNAME,PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		
			return null;
	}
}
