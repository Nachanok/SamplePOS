/*
* Add inventory page
*/
package universalpos.activity;

import universalpos.controller.InventoryController;

import com.example.universalposii.R;
import com.example.universalposii.R.layout;
import com.example.universalposii.R.menu;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InventoryPage_add extends Activity 
{
	private InventoryController inventoryController = new InventoryController(this);
	private InventoryPage inventoryPage = new InventoryPage();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_page_add);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory_page_add, menu);
		return true;
	}
	/**
	 * On click method to invork insert command through inventoryController
	 * @return boolean true mean insert success false mean fail
	 */
	public boolean clickInsert(View v)
	{
		boolean isSuccess = false;
		inventoryController = new InventoryController((Context)this);
		String[] x = new String[6];
		x[0] = ((EditText)findViewById(R.id.ProductID)).getText().toString();
		x[1] = ((EditText)findViewById(R.id.ProductName)).getText().toString();
		x[2] = ((EditText)findViewById(R.id.BuyPrice)).getText().toString();
		x[3] = ((EditText)findViewById(R.id.SellPrice)).getText().toString();
		x[4] = ((EditText)findViewById(R.id.Quantity)).getText().toString();
		x[5] = ((EditText)findViewById(R.id.Detail)).getText().toString();
		isSuccess = inventoryController.insert(x);
		if(isSuccess)
		{
			clearAll(v);
		}
      	return isSuccess;
	}
	/**
	 * Clear all text field method 
	 * @param v
	 */
	public void clearAll(View v)
	{
		((EditText)findViewById(R.id.ProductID)).setText("");
		((EditText)findViewById(R.id.ProductName)).setText("");
		((EditText)findViewById(R.id.BuyPrice)).setText("");
		((EditText)findViewById(R.id.SellPrice)).setText("");
		((EditText)findViewById(R.id.Quantity)).setText("");
		((EditText)findViewById(R.id.Detail)).setText("");
	}
	public void clickCancel(View v)
	{
		super.onBackPressed();
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {
            Intent a = new Intent(this,InventoryPage.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
