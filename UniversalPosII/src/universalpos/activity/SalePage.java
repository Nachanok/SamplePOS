package universalpos.activity;

import universalpos.controller.InventoryController;
import universalpos.controller.SaleController;

import com.example.universalposii.R;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.awt.*;
public class SalePage extends Activity 
{
	private ListView m_listview;
	private int m_SelectedItem=-1;
	private ListView m_listview_cart;
	private int m_SelectedItem_cart=-1;
	private SaleController saleController = new SaleController((Context)this);
	private InventoryController inventoryController = new InventoryController((Context)this);
	private String[] items = new String[1000];
	private String[] items_cart = new String[1000];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_page);
		//for listviewer (Product)
				m_listview = (ListView)findViewById(R.id.listView1);
				items = inventoryController.findAll();
				m_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
				m_listview.setTextFilterEnabled(true);
				m_listview.setOnItemClickListener(new OnItemClickListener(){
					public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
					{		
						m_listview.setSelected(true);
						if(position != m_SelectedItem)
						{	
							m_listview.getChildAt(position).setBackgroundColor(Color.YELLOW);
							if(m_SelectedItem!=-1)
									m_listview.getChildAt(m_SelectedItem).setBackgroundColor(Color.rgb(128, 128, 128));
						}
						m_SelectedItem = position;
				    }});
		//for listviewer (Cart) 
				m_listview_cart = (ListView)findViewById(R.id.listView2);
				//items_cart = saleController.showLineItem();
				//if(items_cart[0].equals(""))
				items_cart = saleController.showProduct();
				//items_cart[0] = "";
				m_listview_cart.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items_cart));
				m_listview_cart.setTextFilterEnabled(true);
				m_listview_cart.setOnItemClickListener(new OnItemClickListener(){
					public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
					{		
						m_listview_cart.setSelected(true);
						if(position != m_SelectedItem_cart)
						{	
							m_listview_cart.getChildAt(position).setBackgroundColor(Color.YELLOW);
							if(m_SelectedItem_cart!=-1)
								m_listview_cart.getChildAt(m_SelectedItem_cart).setBackgroundColor(Color.rgb(128, 128, 128));
						}
						m_SelectedItem_cart = position;
				    }});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sale_page, menu);
		return true;
	}
	public void clickDelete(View v)
	{
		saleController.removeItemFromCart(m_listview_cart.getItemAtPosition(m_SelectedItem_cart)+"");
		items_cart = saleController.showProduct();
		m_listview_cart.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items_cart));
		m_SelectedItem_cart = -1;
		totalUpdate();
	}
	public void clickAdd(View v)
	{
		saleController.addItemToCart(m_listview.getItemAtPosition(m_SelectedItem)+"");
		items_cart = saleController.showProduct();
		m_listview_cart.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items_cart));
		m_SelectedItem = -1;
		totalUpdate();
	}
	public void clickEdit(View v)
	{
		
	}
	public void totalUpdate()
	{
		((TextView)findViewById(R.id.TotalSell)).setText(saleController.sale()+" .-");
	}
}
