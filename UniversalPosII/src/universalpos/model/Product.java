package universalpos.model;

public class Product
{
	private int id;
	private String productID;
	private String productName;
	private double price;
	private double cost;
	private String productDetail;
	public Product(String[] input)
	{
		this.id 			= 	-1;		// -1 is default before get primary id from Database
		this.productID 		=	input[0];
		this.productName 	= 	input[1];
		this.price 			=	Double.parseDouble(input[2]);
		this.cost 			= 	Double.parseDouble(input[3]);
		//this.qnty		 	=	input[4];
		this.productDetail	=	input[5];
	}
	public Product(){
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	
}
