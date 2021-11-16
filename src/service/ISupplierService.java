package service;

import model.Supplier;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface ISupplierService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ISupplierService.class.getName());


	public void addSupplier(Supplier supplier);

	public Supplier getSupplierByID(String supplierID);
	
	public ArrayList<Supplier> getSupplier();
	
	public Supplier updateSupplier(String supplierID, Supplier supplier);

	public void removeSupplier(String supplierID);

}
