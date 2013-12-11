package universalpos.activity;
import java.util.ArrayList;
import universalpos.controller.CustomerController;
import universalpos.model.AdapterListViewDataCustomer;
import universalpos.model.Customer;
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
import android.widget.ListView;
import android.widget.Toast;
public class CustomerPage extends Activity {
	 private AdapterListViewDataCustomer adapterListViewDataCustomer;
	 private ArrayList<Customer> listData = new ArrayList<Customer>();
	 private ListView listViewData;
	 private LinearLayout itemLayout; 
	 private CheckBox checkboxes;
	 private AlertDialog.Builder alertDialog_Del;
	 private CustomerController customerController;
	 private Customer[] customers = null;
	 public CustomerPage() {
		customerController = new CustomerController(this);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        listViewData = (ListView)findViewById(R.id.listView1);
        customers = customerController.findAll();
        if(customers!= null)
        {
        	 System.out.println("!!!!");
        	for(int i=0;i<customers.length;i++)
        		listData.add(customers[i]); 
        	adapterListViewDataCustomer = new AdapterListViewDataCustomer(getBaseContext(),listData);
        	listViewData.setAdapter(adapterListViewDataCustomer);
        }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.customer_page, menu);
		return true;
	}	
	public void goToAddCustomer(View v){
		Intent inventoryintent = new Intent(getApplicationContext(), CustomerPage_add.class);
		startActivity(inventoryintent);
	}
	public void goToEditCustomer(View v){
		int count = listViewData.getAdapter().getCount();
			for (int i = 0; i < count; i++) {
				itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
				checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
						if(checkboxes.isChecked()){
							Intent inventoryintent = new Intent(getApplicationContext(), CustomerPage_edit.class);
							Customer object = customers[i];
							String[] input = new String[6];
							input[0] = object.getCustomerID();
							input[1] = object.getCostomerName();
							input[2] = object.getCustomerTel();
							input[3] = object.getCustomerDetail();
							inventoryintent.putExtra("editLineCustomer", input);
							inventoryintent.putExtra("primaryID", object.getID());
							startActivity(inventoryintent);
							Toast.makeText(CustomerPage.this,customers[i].getCostomerName()+" is editing . . .",Toast.LENGTH_LONG).show();
							onRefresh();
							break;
						}
			}
	}
	public void onRefresh(){
		listData.clear();
		customers = customerController.findAll();
		if(customers!= null){
			for(int i=0;i<customers.length;i++)
				listData.add(customers[i]); 
			adapterListViewDataCustomer = new AdapterListViewDataCustomer(getBaseContext(),listData);
			listViewData.setAdapter(adapterListViewDataCustomer);
		}
		adapterListViewDataCustomer.notifyDataSetChanged();
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
		alertDialog_Del = new AlertDialog.Builder(CustomerPage.this);
		alertDialog_Del.setTitle("Confirm Delete...");
		alertDialog_Del.setMessage("Are you sure to delete a Customer(s)?\n Customer(s) and all details in database will be destroy.");
		alertDialog_Del.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) 
			{
				int count = listViewData.getAdapter().getCount();
					for (int i = 0; i < count; i++) {
						itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
						checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
								if(checkboxes.isChecked()){
									customerController.delete(customers[i].getCustomerID());
									Toast.makeText(CustomerPage.this,customers[i].getCostomerName()+" was deleted . . .",Toast.LENGTH_LONG).show();
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
