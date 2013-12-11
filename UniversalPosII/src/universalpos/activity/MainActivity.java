package universalpos.activity;
import java.text.SimpleDateFormat;
import java.util.Date;

import universalpos.dao.CustomerDAO;
import universalpos.dao.EventRecordDAO;
import universalpos.dao.SaleRecordDAO;
import universalpos.dao.InventoryDAO;
import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
public class MainActivity extends Activity {
	SQLiteDatabase sqliteDB;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InventoryDAO inventoryDao = new InventoryDAO(this);
		inventoryDao.getWritableDatabase();
		CustomerDAO customerDao = new CustomerDAO(this);
		customerDao.getWritableDatabase();
		SaleRecordDAO saleRecordDAO = new SaleRecordDAO(this);
		saleRecordDAO.getWritableDatabase();
		EventRecordDAO eventRecordDAO = new EventRecordDAO(this);
		eventRecordDAO.getWritableDatabase();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void goToInventory(View v){
		Intent inventoryintent = new Intent(getApplicationContext(), InventoryPage.class);
		startActivity(inventoryintent);
	}
	public void goToCustomer(View v){
		 Intent customerIntent = new Intent(getApplicationContext(), CustomerPage.class);
		startActivity(customerIntent);
	}
	public void goToSaleRecord(View v){
		Intent historyIntent = new Intent(getApplicationContext(), SaleRecordPage.class);
		startActivity(historyIntent);
	}
	public void goToEventRecord(View v){
		Intent historyIntent = new Intent(getApplicationContext(), EventRecordPage.class);
		startActivity(historyIntent);
	}
	public void goToSale(View v){
		Intent saleIntent = new Intent(getApplicationContext(), SalePage.class);
		startActivity(saleIntent);
	}
}
