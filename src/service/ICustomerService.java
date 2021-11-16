package service;

import model.Customer;
import java.util.ArrayList;
import java.util.logging.Logger;



public interface ICustomerService {
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ICustomerService.class.getName());


	
	public void addCustomer(Customer customer);


	public Customer getCustomerByID(String customerID);
	
	public ArrayList<Customer> getCustomers();
	
	public Customer updateCustomer(String customerID, Customer customer);


	public void removeCustomer(String customerID);



}
