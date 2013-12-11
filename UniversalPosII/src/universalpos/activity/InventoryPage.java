package universalpos.activity;
import java.util.ArrayList;

import universalpos.controller.HistoryController;
import universalpos.controller.InventoryController;
import universalpos.model.SaleLineItem;
import universalpos.model.adapter.AdapterListViewData;

import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ListView;
public class InventoryPage extends Activity {
	 private AdapterListViewData adapterListViewData; //Adapter List ที่เรากำหนดขึ้นเอง
	 private ArrayList<SaleLineItem> listData = new ArrayList<SaleLineItem>();
	 private SaleLineItem[] saleLineItems = null;
	 private ListView listViewData;
	 private LinearLayout itemLayout;
	 private CheckBox checkboxes;
	 private AlertDialog.Builder alertDialog_Del;
	 private InventoryController inventoryController;
	 private HistoryController historyController = new HistoryController(this);
	 public InventoryPage() {
		inventoryController = new InventoryController(this);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_page);
        listViewData = (ListView)findViewById(R.id.listView1);
        saleLineItems = inventoryController.findAll();
        if(saleLineItems!= null)
        {
        	for(int i=0;i<saleLineItems.length;i++)
        		listData.add(saleLineItems[i]); 
        	adapterListViewData = new AdapterListViewData(getBaseContext(),listData);
        	listViewData.setAdapter(adapterListViewData);
        }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inventory_page, menu);
		return true;
	}	
	public void goToAddItem(View v){
		Intent inventoryintent = new Intent(getApplicationContext(), InventoryPage_add.class);
		startActivity(inventoryintent);
	}
	public void goToEditItem(View v){
		int count = listViewData.getAdapter().getCount();
			for (int i = 0; i < count; i++) {
				itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
				checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
						if(checkboxes.isChecked()){
							Intent inventoryintent = new Intent(getApplicationContext(), InventoryPage_edit.class);
							SaleLineItem object = saleLineItems[i];
							String[] input = new String[6];
							input[0] = object.getProduct().getProductID();
							input[1] = object.getProduct().getProductName();
							input[2] = object.getProduct().getCost()+"";
							input[3] = object.getProduct().getPrice()+"";
							input[4] = object.getQnty()+"";
							input[5] = object.getProduct().getProductDetail();
							inventoryintent.putExtra("editLineItem", input);
							inventoryintent.putExtra("primaryID", object.getProduct().getId());
							startActivity(inventoryintent);
							Toast.makeText(InventoryPage.this,saleLineItems[i].getProduct().getProductName()+" is editing . . .",Toast.LENGTH_LONG).show();
							onRefresh();
							break;
						}
			}
	}
	public void onRefresh(){
		listData.clear();
		saleLineItems = inventoryController.findAll();
		if(saleLineItems!= null){
			for(int i=0;i<saleLineItems.length;i++)
				listData.add(saleLineItems[i]); 
			adapterListViewData = new AdapterListViewData(getBaseContext(),listData);
			listViewData.setAdapter(adapterListViewData);
		}
		adapterListViewData.notifyDataSetChanged();
	}
	public void onDelete(View v)
	{
		boolean isCheckBoxAvalaible = false;
		int count = listViewData.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
			checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
					if(checkboxes.isChecked()){
						isCheckBoxAvalaible = true;
					}
		}
		if(!isCheckBoxAvalaible)
			return;
		alertDialog_Del = new AlertDialog.Builder(InventoryPage.this);
		alertDialog_Del.setTitle("Confirm Delete...");
		alertDialog_Del.setMessage("Are you sure to delete an item(s)?\n Product(s) and all details in database will be destroy.");
		alertDialog_Del.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) 
			{
				int count = listViewData.getAdapter().getCount();
					for (int i = 0; i < count; i++) {
						itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
						checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
								if(checkboxes.isChecked()){
									inventoryController.delete(saleLineItems[i].getProduct().getProductID());
									historyController.insertEventRecord(saleLineItems[i].getQnty()+"ea of "+saleLineItems[i].getProduct().getProductName()+" was deleted.");
									Toast.makeText(InventoryPage.this,"\'"+saleLineItems[i].getQnty()+"\' ea of\'"+saleLineItems[i].getProduct().getProductName()+"\' was deleted . . .",Toast.LENGTH_LONG).show();
								}
					}
					
					onRefresh();
			}});
		alertDialog_Del.setPositiveButton("NO", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) 
			{
				dialog.cancel();
			}});
				alertDialog_Del.show();
	}
	
}