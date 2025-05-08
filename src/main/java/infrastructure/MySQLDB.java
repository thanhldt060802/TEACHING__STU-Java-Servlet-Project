package infrastructure;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class MySQLDB {
	
	private static final String URL = "jdbc:mysql://localhost:3306/my_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345678";
	private static Connection instance;

	public static void openConnection() {
		if (MySQLDB.instance == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				MySQLDB.instance = DriverManager.getConnection(MySQLDB.URL, MySQLDB.USERNAME, MySQLDB.PASSWORD);
				System.out.println("Connect to MySQL successful");
			} catch (ClassNotFoundException e) {
				System.out.println("MySQL JDBC Driver not found");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Connect to MySQL failed");
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection has been opened");
		}
	}

	public static Connection getConnection() {
		if (MySQLDB.instance == null) {
			MySQLDB.openConnection();
		}
		return MySQLDB.instance;
	}

	public static void closeConnection() {
		if (MySQLDB.instance != null) {
			try {
				MySQLDB.instance.close();
				MySQLDB.instance = null;
				
				// Gỡ đăng ký JDBC Driver
				Enumeration<Driver> drivers = DriverManager.getDrivers();
				while (drivers.hasMoreElements()) {
					Driver driver = drivers.nextElement();
					try {
						DriverManager.deregisterDriver(driver);
						System.out.println("Deregister JDBC successful");
					} catch (SQLException e) {
						System.err.println("Deregister JDBC failed: " + e.getMessage());
					}
				}

				// Dọn sạch tài nguyên sử dụng cho JDBC
				AbandonedConnectionCleanupThread.checkedShutdown();
				System.out.println("Shutdown AbandonedConnectionCleanupThread successful");
				
				System.out.println("Close connection to MySQL successful");
			} catch (SQLException e) {
				System.out.println("Close connection to MySQL failed");
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection not opened");
		}
	}

}
