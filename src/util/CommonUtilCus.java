package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.ICustomerService;

public class CommonUtilCus {
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ICustomerService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtilCus.class.getResourceAsStream(CommonConstantsCus.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}


	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstantsCus.CUSTOMER_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstantsCus.CUSTOMER_ID_PREFIX + next;
		}
		return id;
	}

}
