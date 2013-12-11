package universalpos.controller;

import universalpos.model.Inventory;
import universalpos.model.Product;
import universalpos.model.SaleLineItem;
import android.content.Context;

public class InventoryController {
	private Inventory inventory;
	public InventoryController(Context context)
	{
		this.inventory = new Inventory(context);
	}
	public boolean update(int id,Product product,int qnty) {	
		return inventory.update(id, product , qnty);
	}
	public boolean delete(String id) {
		return inventory.delete(id);
	}
	public boolean insert(Product product,int qnty){
		return inventory.insert(product,qnty);
	}
	public SaleLineItem findByKey(String productID) {
		return inventory.findByKey(productID);
	}
	public SaleLineItem[] findAll() {
		return inventory.findAll();
	}
}
