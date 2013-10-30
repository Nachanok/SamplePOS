package universalpos.model;

public class Product
{
	private int id;
	private String productID;
	private String productName;
	private double price;
	private int qnty;
	private String[] data = new String[20];
	public Product(String x)
	{
		data = x.split(" ");
//		for(int i=0;i<data.length;i++)
//		{
//			System.out.println("data["+i+"] ="+data[i]);
//		}
		this.id = Integer.parseInt(data[1]);
		this.productID =  data[3];
		this.productName = data[5];
		this.price = Double.parseDouble(data[9]);
		this.qnty = Integer.parseInt(data[11]);
	}
	public int getQuantity()
	{
		return qnty;
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
