package universalpos.activity;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import universalpos.controller.InventoryController;
import universalpos.dao.InventoryDAO;
import universalpos.model.AdapterListViewData;
import universalpos.model.Product;
import universalpos.model.SaleLineItem;

import com.example.universalposii.R;
import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ListView;
import android.database.Cursor;


public class InventoryPage extends Activity 
{
	 private AdapterListViewData adapterListViewData; //Adapter List ที่เรากำหนดขึ้นเอง
	 private ArrayList<SaleLineItem> listData = new ArrayList<SaleLineItem>();
	 private SaleLineItem[] saleLineItems = null;
	 private ListView listViewData;
	 private LinearLayout itemLayout;
	 private CheckBox checkboxes;
	 private AlertDialog.Builder alertDialog_Del;
	 private InventoryController inventoryController;
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
	}
	public void onRefresh(){
		saleLineItems = inventoryController.findAll();
        for(int i=0;i<saleLineItems.length;i++)
        	listData.add(saleLineItems[i]); 
        adapterListViewData = new AdapterListViewData(getBaseContext(),listData);
        listViewData.setAdapter(adapterListViewData);
	}
	public void onDelete(View v)
	{
			int count = listViewData.getAdapter().getCount();
			System.out.println(count);
				for (int i = 0; i <= count-1; i++) {
					itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
					checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
							if(checkboxes.isChecked())
							{
								System.out.println("!");
								//inventoryController.delete(saleLineItems[i].getProduct().getProductID());
								System.out.println("??");
								//Toast.makeText(InventoryPage.this,saleLineItems[i].getProduct().getProductID()+" are deleting. . .",Toast.LENGTH_LONG).show();
								//onRefresh();
								System.out.println("refresh!");
							}
				}
//		alertDialog_Del = new AlertDialog.Builder(InventoryPage.this);
//		alertDialog_Del.setTitle("Confirm Delete...");
//		alertDialog_Del.setMessage("Are you sure you want delete this?\nThis product and all details\n in database will be destroy forever");
//		alertDialog_Del.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog,int which) 
//			{
//				System.out.println( listViewData);
//			}});
//		alertDialog_Del.setNegativeButton("NO", new DialogInterface.OnClickListener(){
//			public void onClick(DialogInterface dialog, int which) 
//			{
//				dialog.cancel();
//			}});
//	
//				alertDialog_Del.show();
	}
	public void onUpdate(View v){
		
	}

}