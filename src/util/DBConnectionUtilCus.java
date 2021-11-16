package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtilCus  extends CommonUtilCus{
	
	private static Connection connection;

	// This works according to singleton pattern
	private DBConnectionUtilCus() {
	}

	
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		/*
		 * This create new connection objects when connection is closed or it is
		 * null
		 */
		if (connection == null || connection.isClosed()) {

			Class.forName(properties.getProperty(CommonConstantsCus.DRIVER_NAME));
			connection = DriverManager.getConnection(properties.getProperty(CommonConstantsCus.URL),
					properties.getProperty(CommonConstantsCus.USERNAME), properties.getProperty(CommonConstantsCus.PASSWORD));
		}
		return connection;
	}


}
