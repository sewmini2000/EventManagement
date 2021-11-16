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

import model.Event;
import util.CommonConstantsEve;
import util.CommonUtilEve;
import util.DBConnectionUtilEve;
import util.QueryUtilEve;

public class EventServiceImpl implements IEventService{
	
	
	public static final Logger log = Logger.getLogger(EventServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createEventTable();
	}

	private PreparedStatement preparedStatement;

	
	public static void createEventTable() {

		try {
			connection = DBConnectionUtilEve.getDBConnection();
			statement = connection.createStatement();
			
			// statement.executeUpdate(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_DROP_TABLE));
			
			statement.executeUpdate(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	
	@Override
	public void addEvent(Event event) {

		String eventID = CommonUtilEve.generateIDs(getEventIDs());
		
		try {
			connection = DBConnectionUtilEve.getDBConnection();
			
			preparedStatement = connection
					.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_INSERT_EVENTS));
			connection.setAutoCommit(false);
			
			
			event.setEventID(eventID);
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_ONE, event.getEventID());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_TWO, event.getCustomerName());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_THREE, event.getType());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_FOUR, event.getQuantity());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_FIVE, event.getHours());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_SIX, event.getLocation());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_SEVEN, event.getTime());
			preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_EIGHT, event.getDescription());
			
			
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
	public Event getEventByID(String eventID) {

		return actionOnEvent(eventID).get(0);
	}
	
	
	@Override
	public ArrayList<Event> getEvents() {
		
		return actionOnEvent(null);
	}

	
	@Override
	public void removeEvent(String eventID) {

		
		if (eventID != null && !eventID.isEmpty()) {
			
			try {
				connection = DBConnectionUtilEve.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_REMOVE_EVENT));
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_ONE, eventID);
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

	
	private ArrayList<Event> actionOnEvent(String eventID) {

		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			connection = DBConnectionUtilEve.getDBConnection();
			
			if (eventID != null && !eventID.isEmpty()) {
				
				preparedStatement = connection
						.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_GET_EVENT));
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_ONE, eventID);
			}
			
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_ALL_EVENTS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Event event = new Event();
				event.setEventID(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_ONE));
				event.setCustomerName(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_TWO));
				event.setType(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_THREE));
				event.setQuantity(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_FOUR));
				event.setHours(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_FIVE));
				event.setLocation(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_SIX));
				event.setTime(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_SEVEN));
				event.setDescription(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_EIGHT));
				eventList.add(event);
			}

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
		return eventList;
	}

	
	@Override
	public Event updateEvent(String eventID, Event event) {

		
		if (eventID != null && !eventID.isEmpty()) {
			
			try {
				connection = DBConnectionUtilEve.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_UPDATE_EVENT));
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_ONE, event.getCustomerName());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_TWO, event.getType());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_THREE, event.getQuantity());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_FOUR, event.getHours());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_FIVE, event.getLocation());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_SIX, event.getTime());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_SEVEN, event.getDescription());
				preparedStatement.setString(CommonConstantsEve.COLUMN_INDEX_EIGHT, event.getEventID());
			
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
		
		return getEventByID(eventID);
	}
	
	
	private ArrayList<String> getEventIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = DBConnectionUtilEve.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtilEve.queryByID(CommonConstantsEve.QUERY_ID_GET_EVENT_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstantsEve.COLUMN_INDEX_ONE));
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
