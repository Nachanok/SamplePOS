package universalpos.activity;
import universalpos.controller.InventoryController;
import universalpos.controller.SaleController;
import universalpos.model.AdapterListViewData;
import universalpos.model.SaleLineItem;
import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
public class SalePage extends Activity{
private SaleController saleController = new SaleController((Context)this);
private InventoryController inventoryController = new InventoryController((Context)this);
private AdapterListViewData adapterListViewData,adapterListViewDataCart;
private ArrayList<SaleLineItem> listData = new ArrayList<SaleLineItem>();
private ArrayList<SaleLineItem> listDataCart = new ArrayList<SaleLineItem>();
private ArrayList<SaleLineItem> itemAdd = new ArrayList<SaleLineItem>();
private SaleLineItem[] saleLineItems = null;
private ListView listViewData,listViewDataCart;
private LinearLayout itemLayout;
private CheckBox checkboxes;
private String input_dialog_box = "";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_page);
		listViewData = (ListView)findViewById(R.id.listView1);
		listViewDataCart = (ListView)findViewById(R.id.listView2);
		onRefresh();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sale_page, menu);
		return true;
	}
	public void onRefresh(){
		listData.clear();
		saleLineItems = inventoryController.findAll();
		if(saleLineItems!= null){
			for(int i=0;i<saleLineItems.length;i++)
				listData.add(saleLineItems[i]); 
			adapterListViewData = new AdapterListViewData(getBaseContext(),listData);
			listViewData.setAdapter(adapterListViewData);
			adapterListViewData.notifyDataSetChanged();
		}
		adapterListViewDataCart = new AdapterListViewData(getBaseContext(),listDataCart);
		listViewDataCart.setAdapter(adapterListViewDataCart);
		adapterListViewDataCart.notifyDataSetChanged();
	}
	public void onDelete(View v){
		int count = listViewDataCart.getAdapter().getCount();
		for (int i = count-1; i >=0;i--) {
			itemLayout = (LinearLayout)listViewDataCart.getChildAt(i);
			checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
					if(checkboxes.isChecked()){
						listDataCart.remove(i);
					}
		}
		updatePrice();
		onRefresh();
	}
	public void onAdd(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		int count = listViewData.getAdapter().getCount();
		for (int i = count-1; i >=0;i--) {
			itemLayout = (LinearLayout)listViewData.getChildAt(i); // Find by under LinearLayout
			checkboxes = (CheckBox)itemLayout.findViewById(R.id.checkBox);
					if(checkboxes.isChecked()){
						itemAdd.add(saleLineItems[i]);
						builder.setTitle("How much \""+saleLineItems[i].getProduct().getProductName()+"\" do you want to buy?");
						final EditText input = new EditText(this);
						input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
						input.forceLayout();
						builder.setView(input);
						builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						    	input_dialog_box = input.getText().toString();
						    	if(input_dialog_box.equals(""))
						    		return;
						    	for(int n = 0;n<listDataCart.size();n++)
						    		if(listDataCart.get(n).getProduct().getProductID().equals(itemAdd.get(itemAdd.size()-1).getProduct().getProductID())){
						    			listDataCart.get(n).setQnty(listDataCart.get(n).getQnty()+Integer.parseInt(input_dialog_box));
						    			itemAdd.remove(itemAdd.size()-1);
						    			updatePrice();
										onRefresh();
										return;
						    		}
						    	listDataCart.add(new SaleLineItem(itemAdd.get(itemAdd.size()-1).getProduct(),Integer.parseInt(input_dialog_box)));
						    	itemAdd.remove(itemAdd.size()-1);
						    	updatePrice();
								onRefresh();
						    }
						});
						builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						        dialog.cancel();
						    }
						});
						builder.show();
					}
		}
	}
	public void onClearAll(View v){
		listDataCart.clear();
		updatePrice();
		onRefresh();
	}
	public void onSubmit(View v){
		if(saleController.sale(listDataCart))
			onClearAll(v);
	}
	public void onCancel(View v){
		super.onBackPressed();
		Intent a = new Intent(this,MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
	}
	public void updatePrice(){
		double price = 0;
		for(int i = 0;i<listDataCart.size();i++)
				price	+=	listDataCart.get(i).getProduct().getPrice()*listDataCart.get(i).getQnty();
		((TextView)findViewById(R.id.TotalPrice)).setText(price+" .-");
	}
}
