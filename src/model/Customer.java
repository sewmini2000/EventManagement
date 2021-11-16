package model;

public class Customer {
	
	private String CustomerID;
	
	private String FirstName;

	private String LastName;

	private String NIC;

	private String Mobile;

	private String Address;
	
	private String Birthday;
	
	private String Gender;

	@Override
	public String toString() {
		return "Customer [CustomerID=" + CustomerID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", NIC="
				+ NIC + ", Mobile=" + Mobile + ", Address=" + Address + ", Birthday=" + Birthday + ", Gender=" + Gender
				+ "]";
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	


}
