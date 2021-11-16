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

import model.Customer;
import util.CommonConstantsCus;
import util.CommonUtilCus;
import util.DBConnectionUtilCus;
import util.QueryUtilCus;

public class CustomerServiceImpl implements ICustomerService{
	
	public static final Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createCustomerTable();
	}

	private PreparedStatement preparedStatement;

	
	public static void createCustomerTable() {

		try {
			connection = DBConnectionUtilCus.getDBConnection();
			statement = connection.createStatement();
			
			// statement.executeUpdate(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_DROP_TABLE));
			
			statement.executeUpdate(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	
	@Override
	public void addCustomer(Customer customer) {

		String customerID = CommonUtilCus.generateIDs(getCustomerIDs());
		
		try {
			connection = DBConnectionUtilCus.getDBConnection();
			
			preparedStatement = connection
					.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_INSERT_CUSTOMERS));
			connection.setAutoCommit(false);
			
			
			customer.setCustomerID(customerID);
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_ONE, customer.getCustomerID());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_TWO, customer.getFirstName());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_THREE, customer.getLastName());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_FOUR, customer.getNIC());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_FIVE, customer.getMobile());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_SIX, customer.getAddress());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_SEVEN, customer.getBirthday());
			preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_EIGHT, customer.getGender());
			
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
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

	
	@Override
	public Customer getCustomerByID(String customerID) {

		return actionOnCustomer(customerID).get(0);
	}
	
	
	@Override
	public ArrayList<Customer> getCustomers() {
		
		return actionOnCustomer(null);
	}

	
	@Override
	public void removeCustomer(String customerID) {

		
		if (customerID != null && !customerID.isEmpty()) {
			
			try {
				connection = DBConnectionUtilCus.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_REMOVE_CUSTOMER));
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_ONE, customerID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				
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

	
	private ArrayList<Customer> actionOnCustomer(String customerID) {

		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			connection = DBConnectionUtilCus.getDBConnection();
			
			if (customerID != null && !customerID.isEmpty()) {
				
				preparedStatement = connection
						.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_GET_CUSTOMER));
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_ONE, customerID);
			}
			
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_ALL_CUSTOMERS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_ONE));
				customer.setFirstName(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_TWO));
				customer.setLastName(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_THREE));
				customer.setNIC(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_FOUR));
				customer.setMobile(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_FIVE));
				customer.setAddress(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_SIX));
				customer.setBirthday(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_SEVEN));
				customer.setGender(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_EIGHT));
				customerList.add(customer);
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
		return customerList;
	}

	
	@Override
	public Customer updateCustomer(String customerID, Customer customer) {

		
		if (customerID != null && !customerID.isEmpty()) {
			
			try {
				connection = DBConnectionUtilCus.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_UPDATE_CUSTOMER));
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_ONE, customer.getFirstName());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_TWO, customer.getLastName());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_THREE, customer.getNIC());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_FOUR, customer.getMobile());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_FIVE, customer.getAddress());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_SIX, customer.getBirthday());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_SEVEN, customer.getGender());
				preparedStatement.setString(CommonConstantsCus.COLUMN_INDEX_EIGHT, customer.getCustomerID());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
			
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
		
		return getCustomerByID(customerID);
	}
	
	
	private ArrayList<String> getCustomerIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = DBConnectionUtilCus.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtilCus.queryByID(CommonConstantsCus.QUERY_ID_GET_CUSTOMER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstantsCus.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
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
