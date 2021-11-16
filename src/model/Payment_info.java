package model;
//Payment details
public class Payment_info {
	
	private String PaymentID;

	
	private String CustomerName;

	private String Address;
	
	private String CardType;
	
	private String CardNumber;
	
	private String ExpDate;
	
	private String Cvc;

	private String Total;
	
	/**
	 * @return the paymentID
	 */
	public String getPaymentID() {
		return PaymentID;
	}

	public void setPaymentID(String paymentID) {
		PaymentID = paymentID;
	}


	/**
	 * @return the customer name
	 */
	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	/**
	 * @return the billing Address
	 */
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * @return the card type
	 */
	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}
	
	/**
	 * @return the card number
	 */
	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	
	/**
	 * @return the expiration date
	 */
	public String getExpDate() {
		return ExpDate;
	}

	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	/**
	 * @return the cvc number
	 */
	public String getCvc() {
		return Cvc;
	}

	public void setCvc(String cvc) {
		Cvc = cvc;
	}
	/**
	 * @return the Total
	 */
	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}


	@Override
	public String toString() {
		
		return "Payment ID = " + PaymentID + "\n" + "\n" +"Customer Name = " + CustomerName + "\n" + "Billing Address = " + Address + "\n" + "Card Type = " + CardType
				+ "Card Number = " + CardNumber + "Expiration Date = " + ExpDate + "cvc Number = " + Cvc + "Total = " + Total;
	}
}
