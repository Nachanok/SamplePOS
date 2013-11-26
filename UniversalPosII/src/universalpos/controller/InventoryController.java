package universalpos.controller;

import universalpos.model.Inventory;
import universalpos.model.Product;
import universalpos.model.SaleLineItem;
import android.content.Context;

public class InventoryController 
{
	private Inventory inventory;
	public InventoryController(Context context)
	{
		this.inventory = new Inventory(context);
	}
	public boolean update(String[] x) {	
		// TODO complete it
		return false;
	}
	public boolean delete(String id) {
		return inventory.delete(id);
	}
	public boolean insert(Product product,int qnty){
		return inventory.insert(product,qnty);
	}
	public String[] findByKey(String x) {
		// TODO complete it
		return null;
	}
	public SaleLineItem[] findAll() {
		return inventory.findAll();
	}
}
