package universalpos.controller;
import java.util.ArrayList;
import universalpos.model.Inventory;
import universalpos.model.SaleLineItem;
import universalpos.model.SaleRecord;
import android.content.Context;
// TODO Change to MVC
public class SaleController 
{
	private Inventory inventory;
	private HistoryController historyController;
	private SaleRecord saleRecord;
	private String itemsList;
	public SaleController(Context context) {
		inventory = new Inventory(context);
		historyController = new HistoryController(context);
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
		int count_event = 0;
		itemsList="";
		double effect = 0;
		for(int i = 0;i<saleLineItems.size();i++){
			SaleLineItem dbLineItem = inventory.findByKey(saleLineItems.get(i).getProduct().getProductID());
			inventory.update(saleLineItems.get(i).getProduct().getId(),saleLineItems.get(i).getProduct(), dbLineItem.getQnty()-saleLineItems.get(i).getQnty());
			count_event+=saleLineItems.get(i).getQnty();
			itemsList+= saleLineItems.get(i).getProduct().getProductName()+"\n";
			effect+= saleLineItems.get(i).getProduct().getPrice()*saleLineItems.get(i).getQnty();
		}
		historyController.insertEventRecord("Sale "+saleLineItems.size()+" product(s)\nTotal "+count_event+" ea");
		saleRecord = new SaleRecord();
		saleRecord.setItemsList(itemsList);
		saleRecord.setCustomerID("Default ID");
		saleRecord.setCustomerName("Default");
		saleRecord.setEffect(effect+"");
		saleRecord.setDetail("N/A");
		historyController.insertSaleRecord(saleRecord);
		return 	true;
	}
	public boolean cancel(){
		return false;
	}
}
