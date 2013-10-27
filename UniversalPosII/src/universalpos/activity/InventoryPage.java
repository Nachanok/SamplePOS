package universalpos.activity;

import universalpos.controller.InventoryController;

import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InventoryPage extends Activity 
{
	private String[] items = null;
	private ListView m_listview;
    
   // ArrayAdapter<String> adapter =
//    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//    m_listview.setAdapter(adapter);
//	m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
//          String selectedFromList =(String) (m_listview.getItemAtPosition(myItemInt));
//
//        }                 
//  });
	
	private InventoryController inventoryController = new InventoryController(this);	//Create InventoryController
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_page);
		m_listview = (ListView)findViewById(R.id.listView1);
		items = inventoryController.findAll();
		m_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
		m_listview.setTextFilterEnabled(true);
		m_listview.setOnItemClickListener(
						new OnItemClickListener()
						{
								public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
								{		
									AlertDialog.Builder adb = new AlertDialog.Builder(
									InventoryPage.this);
									adb.setTitle("ListView OnClick");
									adb.setMessage("Selected Item is = "
									+ m_listview.getItemAtPosition(position));
									adb.setPositiveButton("Ok", null);
									adb.show();                     
		                        }
		                });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory_page, menu);
		return true;
	}



	
	/**
	 * on click method to create an InventoryPage_add (add item page) and display
	 * @param v default android argument
	 */
	public void goToAddItem(View v)
	{
		Intent inventoryintent = new Intent(getApplicationContext(), InventoryPage_add.class); //go to add product page
		startActivity(inventoryintent);
	}
	/**
	 * on click method to refresh list of item
	 * @param v default android argument
	 */
	public void refresh(View v)
	{
		//m_listview 
		m_listview.setClickable(true);
	    String[] items = inventoryController.findAll();
	    ArrayAdapter<String> adapter =
	    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	    m_listview.setAdapter(adapter);
	}
	/**
	 * on click method to delete item
	 * @param v default android argument
	 */
	public void delete(View v)
	{
	}

}