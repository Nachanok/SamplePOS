package universalpos.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class SaleLineItem 
{
	private Product product;
	//private Product[] productList = new Product[1000];
	private int productAvailable = 0;
	private List<Product> productList = new ArrayList<Product>();
	public SaleLineItem()
	{
		
	}
	public boolean addItemToCart(String x)
	{
		product = new Product(x);
		boolean isIn = false;
		productList.add(product);
//		for(int i = 0;i<productList.length;i++)
//		{
//			if(!productList.equals(""))
//			if(productList[i].getProductID().equals(product.getProductID()) && productList[i].getProductName().equals(product.getProductName()))
//			{
//				productList[i].setQuantity(product.getQuantity());
//				isIn = true;
//				break;
//			}
//		}
//		if(!isIn)
//		{
//			for(int i = 0;i<productList.length;i++)
//			{
//				if(productList[i].equals(""))
//					productList[i] = product;
//			}
//		}
		return true;
	}
	public int checkStock()
	{
//		for(int i = 0;i<productList.length;i++)
//		{
//			if(productList[i].getProductID()!="")
//				productAvailable++;
//		}
		return productAvailable;
	}
	public String[] showProduct()
	{
//		String[] output = null;
//		if(checkStock()!=0)
//			output = new String[checkStock()];
//		else
//			output = new String[]{" "};
//		
//		int pointer = 0;
//		for(int i = 0;i<productList.length;i++)
//		{
//			if(!productList[i].equals(""))
//			{
//				output[pointer] = productList[i].getID()+productList[i].getProductID()+productList[i].getProductName()+productList[i].getPrice()+productList[i].getQuantity();
//				pointer++;
//			}
//		}
		int pointer = 0;
		String[] output = new String[productList.size()];
		for(int i = 0;i<productList.size();i++)
		{
			output[pointer] = "ID : \t"+productList.get(i).getID()+"\nProductID : \t"+ productList.get(i).getProductID()+"\nProductName : \t" + productList.get(i).getProductName()+"\nPrice : \t" + productList.get(i).getPrice()+"\nQuantity : \t" + productList.get(i).getQuantity();
				pointer++;
		}
		return output;
	}
	public double getTotal()
	{
		double total = 0.0;
		for(int i = 0;i<productList.size();i++)
		{
			total += productList.get(i).getTotalPrice();
		}
		return total;
	}
	public boolean removeItemFromCart(String x) 
	{
		// TODO Auto-generated method stub
		return false;
	}
}
