package universalpos.model;

public class Customer{
	private String customerID,costomerName,customerTel,customerDetail;
	private int id=-1;
	public Customer(String customerID, String costomerName, String customerTel,String customerDetail) {
		this.customerID = customerID;
		this.costomerName = costomerName;
		this.customerTel = customerTel;
		this.customerDetail = customerDetail;
	}
	public Customer(){
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCostomerName() {
		return costomerName;
	}
	public void setCostomerName(String costomerName) {
		this.costomerName = costomerName;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCustomerDetail() {
		return customerDetail;
	}
	public void setCustomerDetail(String customerDetail) {
		this.customerDetail = customerDetail;
	}
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return this.id;
	}
}
