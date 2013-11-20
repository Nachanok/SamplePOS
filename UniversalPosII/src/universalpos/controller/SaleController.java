package universalpos.controller;

import universalpos.dao.InventoryDAO;
import universalpos.model.Inventory;
import universalpos.model.Product;
import universalpos.model.SaleLineItem;
import android.content.Context;

public class SaleController 
{
	private SaleLineItem saleLineItem = new SaleLineItem();
	private Inventory inventory;
	public SaleController(Context context) 
	{
		inventory = new Inventory(context);
	}
	public boolean addItemToCart(String x)
	{
		return saleLineItem.addItemToCart(x);
	}
	public boolean removeItemFromCart(String x)
	{
		return saleLineItem.removeItemFromCart(x);
	}
	public String[] showProduct()
	{
		return saleLineItem.showProduct();
	}
	public String[] showLineItem()
	{
		return null;
	}
	public double sale()
	{
		//todo make history and remove quantity
		return 	saleLineItem.getTotal();
	}
	public boolean cancel()
	{
		saleLineItem.removeAll();
		return false;
	}
}
