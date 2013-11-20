package universalpos.controller;

import universalpos.model.Inventory;
import android.content.Context;

public class InventoryController 
{
	private Inventory inventory;
	public InventoryController(Context context)
	{
		this.inventory = new Inventory(context);
	}
	public boolean update(String[] x) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean delete(String id) 
	{
		return inventory.delete(id);
	}
	
	public boolean insert(String[] x)
	{
		return inventory.insert(x);
	}

	public String[] findByKey(String x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[] findAll() 
	{
		return inventory.findAll();
	}
}
