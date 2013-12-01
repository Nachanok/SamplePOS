package universalpos.controller;
import java.util.ArrayList;
import universalpos.model.Inventory;
import universalpos.model.SaleLineItem;
import android.content.Context;
// TODO Change to MVC
public class SaleController 
{
	private Inventory inventory;
	public SaleController(Context context) {
		inventory = new Inventory(context);
	}
	public boolean addItemToCart(String x){
		return true;
	}
	public boolean removeItemFromCart(String x){
		return true;
	}
	public String[] showProduct(){
		return null;
	}
	public String[] showLineItem(){
		return null;
	}
	public boolean sale(ArrayList<SaleLineItem> saleLineItems){
		for(int i = 0;i<saleLineItems.size();i++){	
			SaleLineItem dbLineItem = inventory.findByKey(saleLineItems.get(i).getProduct().getProductID());
			inventory.update(saleLineItems.get(i).getProduct().getId(),saleLineItems.get(i).getProduct(), dbLineItem.getQnty()-saleLineItems.get(i).getQnty());
		}
		return 	true;
	}
	public boolean cancel(){
		return false;
	}
}
