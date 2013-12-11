package universalpos.model;

public class SaleRecord {
	private String customerName="",CustomerID="",effect="",itemsList="",detail="";
	private int day,month,year,hour,min,id = -1;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public SaleRecord(){
	}
	public String getTime(){
		return this.day+"/"+this.month+"/"+this.year+" "+this.hour+":"+this.min;
	}
	public int getDay() {
		return day;
	}
	public String getItemsList() {
		return itemsList;
	}
	public void setItemsList(String itemsList) {
		this.itemsList = itemsList;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
