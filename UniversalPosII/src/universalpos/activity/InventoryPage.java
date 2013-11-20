package universalpos.activity;

import javax.xml.datatype.Duration;

import universalpos.controller.InventoryController;
import universalpos.dao.InventoryDAO;

import com.example.universalposii.R;
import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
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
	private String[] items = null;
	private AlertDialog.Builder alertDialog_Del;
	private ListView m_listview;
	private int m_SelectedItem = -1;
	private InventoryController inventoryController = new InventoryController(this);//Create InventoryController
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_page);
		
		//for listviewer
		m_listview = (ListView)findViewById(R.id.listView1);
		items = inventoryController.findAll();
		m_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
		m_listview.setTextFilterEnabled(true);
		m_listview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
			{		
				//System.out.println(m_listview.getSelectedItem().toString());
				m_listview.setSelected(true);
				Toast.makeText(getApplicationContext(),position+"!!",Toast.LENGTH_LONG).show(); 
				if(position != m_SelectedItem)
				{	
					if(m_SelectedItem!=-1)
						m_listview.getChildAt(m_SelectedItem).setBackgroundColor(Color.rgb(128, 128, 128));
					m_listview.getChildAt(position).setBackgroundColor(Color.WHITE);
				}
				m_SelectedItem = position;
		    }});
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
		items = inventoryController.findAll();
		m_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
		m_SelectedItem =-1;
	}
	public void refresh( )
	{
		items = inventoryController.findAll();
		m_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
		m_SelectedItem =-1;
	}
	/**
	 * on click method to delete item
	 * @param v default android argument
	 */
	public void clickDelete(View v)
	{
		if(m_SelectedItem>=0)
		{
			//for delete dialog
			alertDialog_Del = new AlertDialog.Builder(InventoryPage.this);
			alertDialog_Del.setTitle("Confirm Delete...");
			alertDialog_Del.setMessage("Are you sure you want delete this?\nThis product and all details\n in database will be destroy forever");
			alertDialog_Del.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) 
				{
					String[] spliter = new String[300];
					System.out.println( m_listview.getItemAtPosition(m_SelectedItem).toString());
					spliter = m_listview.getItemAtPosition(m_SelectedItem).toString().split(" ");
					System.out.println(spliter[0]);
					if(inventoryController.delete(spliter[0]))
					{
						refresh();
						m_SelectedItem=-1;
						Toast.makeText(getApplicationContext(), "Delete Successful!", Toast.LENGTH_SHORT).show();
					}
				}});
			alertDialog_Del.setNegativeButton("NO", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) 
				{
					dialog.cancel();
				}});
		
					alertDialog_Del.show();
		}
	}
	public void clickEdit(View v)
	{
		
	}

}