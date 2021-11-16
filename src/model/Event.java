package model;

public class Event {
	
	private String EventID;
	
	private String CustomerName;
	
	private String Type;

	private String Quantity;

	private String Hours;

	private String Location;

	private String Time;
	
	private String Description;

	@Override
	public String toString() {
		return "Event [EventID=" + EventID + ", CustomerName=" + CustomerName + ", Type=" + Type + ", Quantity=" + Quantity + ", Hours=" + Hours
				+ ", Location=" + Location + ", Time=" + Time + ", Description=" + Description + "]";
	}

	public String getEventID() {
		return EventID;
	}

	public void setEventID(String eventID) {
		EventID = eventID;
	}
	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getHours() {
		return Hours;
	}

	public void setHours(String hours) {
		Hours = hours;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
	

	

}
