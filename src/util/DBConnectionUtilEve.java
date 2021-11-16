package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtilEve extends CommonUtilEve {
	
	private static Connection connection;

	// This works according to singleton pattern
	private DBConnectionUtilEve() {
	}

	
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		/*
		 * This create new connection objects when connection is closed or it is
		 * null
		 */
		if (connection == null || connection.isClosed()) {

			Class.forName(properties.getProperty(CommonConstantsEve.DRIVER_NAME));
			connection = DriverManager.getConnection(properties.getProperty(CommonConstantsEve.URL),
					properties.getProperty(CommonConstantsEve.USERNAME), properties.getProperty(CommonConstantsEve.PASSWORD));
		}
		return connection;
	}

}
