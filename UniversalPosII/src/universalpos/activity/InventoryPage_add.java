package universalpos.activity;

import com.example.universalposii.R;
import com.example.universalposii.R.layout;
import com.example.universalposii.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class InventoryPage_add extends Activity {

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

}
