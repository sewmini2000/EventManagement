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

import model.Payment_info;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;
import util.QueryUtil;

public class PaymentServiceImpl implements IPaymentService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createPaymentTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Payment table in the database and
	 * recreate table structure to insert payment entries
	 * 
	 */
	public static void createPaymentTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			// statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add payment details
	 */
	@Override
	public void addPayment(Payment_info payment) {

		String paymentID = CommonUtil.generateIDs(getPaymentIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_PAYMENT));
			connection.setAutoCommit(false);
			
			//Generate payment IDs
			payment.setPaymentID(paymentID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, payment.getPaymentID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, payment.getCustomerName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, payment.getAddress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, payment.getCardType());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, payment.getCardNumber());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, payment.getExpDate());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, payment.getCvc());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, payment.getTotal());
			
			// Add payment
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
	 * Employee details can be retrieved based on the provided payment ID
	 */
	@Override
	public Payment_info getPaymentByID(String paymentID) {

		return actionOnPayment(paymentID).get(0);
	}
	
	/**
	 * Get all list of payments
	 */
	@Override
	public ArrayList<Payment_info> getPayment() {
		
		return actionOnPayment(null);
	}

	/**
	 * This method delete an payment based on the provided ID
	 */
	@Override
	public void removePayment(String paymentID) {

		// Before deleting check whether payment ID is available
		if (paymentID != null && !paymentID.isEmpty()) {
			/*
			 * Remove payment query will be retrieved from PaymentQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_PAYMENT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, paymentID);
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
	 * This performs GET payment by ID and Display all payment
	 */
	private ArrayList<Payment_info> actionOnPayment(String paymentID) {

		ArrayList<Payment_info> paymentList = new ArrayList<Payment_info>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching payment it checks whether payment ID is
			 * available
			 */
			if (paymentID != null && !paymentID.isEmpty()) {

				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_PAYMENT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, paymentID);
			}
			/*
			 * If payment ID is not provided for get payment option it display
			 * all payments
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_PAYMENT));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Payment_info payment = new Payment_info();
				payment.setPaymentID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				payment.setCustomerName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				payment.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				payment.setCardType(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				payment.setCardNumber(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				payment.setExpDate(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				payment.setCvc(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				payment.setTotal(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				
				paymentList.add(payment);
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
		return paymentList;
	}

	/**
	 * Get the updated employee
	 */
	@Override
	public Payment_info updatePayment(String paymentID, Payment_info payment) {

		/*
		 * Before fetching payment it checks whether payment ID is available
		 */
		if (paymentID != null && !paymentID.isEmpty()) {
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_PAYMENT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, payment.getCustomerName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, payment.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, payment.getCardType());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, payment.getCardNumber());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, payment.getExpDate());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, payment.getCvc());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, payment.getTotal());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, payment.getPaymentID());
				
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
		// Get the updated payment
		return getPaymentByID(paymentID);
	}
	
	private ArrayList<String> getPaymentIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_PAYMENT_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
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
