package model;
/**
 * This is the Suppliers model class

 */
public class Supplier {

	private String SupplierID;
	
	private String Name;

	private String ContactNo;

	private String Address;

	private String ItemsServices;

	private String Price;
	
	private String Discounts;
	

	/**
	 * @return the supplierID
	 */
	public String getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(String supplierID) {
		SupplierID = supplierID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the contact no
	 */
	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo ) {
		ContactNo = contactNo;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	/**
	 * @return the ItemsServices
	 */
	public String getItemsServices() {
		return ItemsServices;
	}

	public void setItemsServices(String itemsServices) {
		ItemsServices = itemsServices;
	}


	/**
	 * @return the Price
	 */
	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	/**
	 * @return the Discounts
	 */
	public String getDiscounts() {
		return Discounts;
	}

	public void setDiscounts(String discounts) {
		Discounts = discounts;
	}


	@Override
	public String toString() {
		
		return "Supplier ID = " + SupplierID + "\n" + "Name = " + Name + "\n" + "Contact No = " + ContactNo + "\n"
				+ "Address = " + Address + "\n"  + "Items and Services = " + ItemsServices + "Price = " + Price + "Discounts = " + Discounts;
	}
}
