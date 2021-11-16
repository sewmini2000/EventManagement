package service;

import model.Event;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface IEventService {
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IEventService.class.getName());


	public void addEvent(Event event);

	public Event getEventByID(String eventID);
	

	public ArrayList<Event> getEvents();
	

	public Event updateEvent(String eventID, Event event);


	public void removeEvent(String eventID);

	

}
