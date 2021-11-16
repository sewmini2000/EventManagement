package service;

import model.Payment_info;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface IPaymentService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IPaymentService.class.getName());


	/**
	 * Add payments for payment table
	 */
	public void addPayment(Payment_info payment);

	public Payment_info getPaymentByID(String paymentID);
	
	/**
	 * Get all list of payment history
	 */
	public ArrayList<Payment_info> getPayment();
	
	/**
	 * Update existing payments
	 */
	public Payment_info updatePayment(String paymentID, Payment_info payment);

	/**
	 * Remove existing payments
	 */
	public void removePayment(String paymentID);

}
