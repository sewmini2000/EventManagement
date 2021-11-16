package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.IEventService;

public class CommonUtilEve {
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IEventService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtilEve.class.getResourceAsStream(CommonConstantsEve.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}


	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstantsEve.EVENT_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstantsEve.EVENT_ID_PREFIX + next;
		}
		return id;
	}

}
