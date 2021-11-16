package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Supplier;
import util.SupplierCommonConstants;
import util.SupplierCommonUtil;
import util.SupplierDBConnectionUtil;
import util.SupplierQueryUtil;

public class SupplierServiceImpl implements ISupplierService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(SupplierServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createSupplierTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Supplier table in the database and
	 * recreate table structure to insert supplier entries
	 */
	public static void createSupplierTable() {

		try {
			connection = SupplierDBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			// statement.executeUpdate(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_DROP_TABLE));
			// Create new supplier table as per SQL query available in
			// Query.xml
			statement.executeUpdate(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of supplier for as a batch from the selected supplier List

	 */
	@Override
	public void addSupplier(Supplier supplier) {

		String supplierID = SupplierCommonUtil.generateIDs(getSupplierIDs());
		
		try {
			connection = SupplierDBConnectionUtil.getDBConnection();

			preparedStatement = connection
					.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_INSERT_SUPPLIER));
			connection.setAutoCommit(false);
			
			//Generate supplier IDs
			supplier.setSupplierID(supplierID);
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_ONE, supplier.getSupplierID());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_TWO, supplier.getName());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_THREE, supplier.getContactNo());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_FOUR, supplier.getAddress());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_FIVE, supplier.getItemsServices());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_SIX, supplier.getPrice());
			preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_SEVEN, supplier.getDiscounts());

			// Add supplier
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Supplier details can be retrieved based on the provided supplier ID
	 */
	@Override
	public Supplier getSupplierByID(String supplierID) {

		return actionOnSupplier(supplierID).get(0);
	}
	
	/**
	 * Get all list of supplier
	 */
	@Override
	public ArrayList<Supplier> getSupplier() {
		
		return actionOnSupplier(null);
	}

	/**
	 * This method delete an supplier based on the provided ID
	 */
	@Override
	public void removeSupplier(String supplierID) {

		// Before deleting check whether supplier ID is available
		if (supplierID != null && !supplierID.isEmpty()) {

			try {
				connection = SupplierDBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_REMOVE_SUPPLIER));
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_ONE, supplierID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET supplier by ID and Display all supplier
	 */
	private ArrayList<Supplier> actionOnSupplier(String supplierID) {

		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			connection = SupplierDBConnectionUtil.getDBConnection();
			
			if (supplierID != null && !supplierID.isEmpty()) {
				preparedStatement = connection
						.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_GET_SUPPLIER));
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_ONE, supplierID);
			}
			/*
			 * If supplier ID is not provided for get supplier option it display
			 * all suppliers
			 */
			else {
				preparedStatement = connection
						.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_ALL_SUPPLIER));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierID(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_ONE));
				supplier.setName(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_TWO));
				supplier.setContactNo(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_THREE));
				supplier.setAddress(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_FOUR));
				supplier.setItemsServices(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_FIVE));
				supplier.setPrice(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_SIX));
				supplier.setDiscounts(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_SEVEN));
				supplierList.add(supplier);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return supplierList;
	}

	/**
	 * Get the updated supplier
	 */
	@Override
	public Supplier updateSupplier(String supplierID, Supplier supplier) {

		/*
		 * Before fetching supplier it checks whether supplier ID is available
		 */
		if (supplierID != null && !supplierID.isEmpty()) {

			try {
				connection = SupplierDBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_UPDATE_SUPPLIER));
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_ONE, supplier.getName());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_TWO, supplier.getContactNo());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_THREE, supplier.getAddress());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_FOUR, supplier.getItemsServices());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_FIVE, supplier.getPrice());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_SIX, supplier.getDiscounts());
				preparedStatement.setString(SupplierCommonConstants.COLUMN_INDEX_SEVEN, supplier.getSupplierID());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated supplier
		return getSupplierByID(supplierID);
	}
	

	private ArrayList<String> getSupplierIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of supplier IDs will be retrieved from SupplierQuery.xml
		 */
		try {
			connection = SupplierDBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(SupplierQueryUtil.queryByID(SupplierCommonConstants.QUERY_ID_GET_SUPPLIER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(SupplierCommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
}
