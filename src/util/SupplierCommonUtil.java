package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.ISupplierService;

public class SupplierCommonUtil {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ISupplierService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(SupplierQueryUtil.class.getResourceAsStream(SupplierCommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add new Supplier ID
	 */
	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = SupplierCommonConstants.SUPPLIER_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = SupplierCommonConstants.SUPPLIER_ID_PREFIX + next;
		}
		return id;
	}
}
