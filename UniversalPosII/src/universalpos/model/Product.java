package universalpos.model;

public class Product
{
	private int id;
	private String productID;
	private String productName;
	private double price;
	private double qnty;
	private String[] data = new String[20];
	public Product(String x)
	{
		data = x.split(" ");
//		for(int i=0;i<data.length;i++)
//		{
//			System.out.println("data["+i+"] ="+data[i]);
//		}
//		this.id = 1;//Integer.parseInt(data[1]);
//		this.productID =  "1";//data[3];
		this.productName = data[0];
		this.price = Double.parseDouble(data[2]);
		this.qnty = Double.parseDouble(data[1]);
	}
	public int getQuantity()
	{
		return (int)qnty;
	}
	public boolean setQuantity(int input)
	{
		qnty = input;
		return true;
	}
	public double getTotalPrice()
	{
		return price*qnty;
	}
	public double getPrice()
	{
		return price;
	}
	public boolean setPrice(double input)
	{
		price = input;
		return true;
	}
	public int getID()
	{
		return id;
	}
	public String getProductID()
	{
		return productID;
	}
	public String getProductName()
	{
		return productName;
	}
	public boolean isEqual(Product product)
	{
		return true;
	}
}
