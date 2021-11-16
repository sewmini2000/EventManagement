package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SupplierDBConnectionUtil extends CommonUtil {

	private static Connection connection;

	// This works according to singleton pattern
	private SupplierDBConnectionUtil() {
	}

	/**
	 * Create Database connection for the given URL, Username and Password
	 */
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		/*
		 * This create new connection objects when connection is closed or it is
		 * null
		 */
		if (connection == null || connection.isClosed()) {

			Class.forName(properties.getProperty(SupplierCommonConstants.DRIVER_NAME));
			connection = DriverManager.getConnection(properties.getProperty(SupplierCommonConstants.URL),
					properties.getProperty(SupplierCommonConstants.USERNAME), properties.getProperty(SupplierCommonConstants.PASSWORD));
		}
		return connection;
	}
}










