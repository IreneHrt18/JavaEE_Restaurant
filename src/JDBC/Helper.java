package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Helper {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	/**
	 * 
	 */
	static{
		Properties properties = new Properties();
		InputStream iStream = Helper.class.getResourceAsStream("dbconfig.property");
		try {
			properties.load(iStream);
			driver = properties.getProperty("Driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			System.out.println("connection success");
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void free(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			// 释放ResultSet对象
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放Statement对象
				if (null != ps) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 释放Connection对象
					if (null != conn) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("disconnect");
	}

}
