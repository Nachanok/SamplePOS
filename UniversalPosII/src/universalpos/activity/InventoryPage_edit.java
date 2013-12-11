package universalpos.activity;
import universalpos.controller.HistoryController;
import universalpos.controller.InventoryController;
import universalpos.model.Product;
import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class InventoryPage_edit extends Activity {
	private InventoryController inventoryController = new InventoryController(this);
	private HistoryController historyController = new HistoryController(this);
	private int primaryID=-1;
	private String old_event,new_event;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invenroty_page_edit);
		Bundle extras = getIntent().getExtras();
		Intent i = getIntent();
		primaryID = extras.getInt("primaryID");
		String[] editLineItem = i.getStringArrayExtra("editLineItem");
		((EditText)findViewById(R.id.ProductID)).setText(editLineItem[0]);
		((EditText)findViewById(R.id.ProductName)).setText(editLineItem[1]);
		((EditText)findViewById(R.id.BuyPrice)).setText(editLineItem[2]);
		((EditText)findViewById(R.id.SellPrice)).setText(editLineItem[3]);
		((EditText)findViewById(R.id.Quantity)).setText(editLineItem[4]);
		((EditText)findViewById(R.id.Detail)).setText(editLineItem[5]);
		old_event ="Product : "+editLineItem[0]+"\nName : "+editLineItem[1]+"\nBuyPrice : "+editLineItem[2]+"\nSellPrice : "+editLineItem[3]+"\nQuantity : "+editLineItem[4]+"\nDetail : "+editLineItem[5];
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inventory_page_edit, menu);
		return true;
	}
	public boolean onUpdate(View v){
		boolean isSuccess = false;
		int qnty = -1;
		inventoryController = new InventoryController((Context)this);
		String[] input = new String[6];
		input[0] = ((EditText)findViewById(R.id.ProductID)).getText().toString();
		input[1] = ((EditText)findViewById(R.id.ProductName)).getText().toString();
		input[2] = ((EditText)findViewById(R.id.BuyPrice)).getText().toString();
		input[3] = ((EditText)findViewById(R.id.SellPrice)).getText().toString();
		input[4] = ((EditText)findViewById(R.id.Quantity)).getText().toString();
		input[5] = ((EditText)findViewById(R.id.Detail)).getText().toString();
		new_event ="Product : "+input[0]+"\nName : "+input[1]+"\nBuyPrice : "+input[2]+"\nSellPrice : "+input[3]+"\nQuantity : "+input[4]+"\nDetail : "+input[5];
		Product product = new Product(input);
		if(!input[4].equals(""))
		qnty = Integer.parseInt(input[4]);
		isSuccess = inventoryController.update(primaryID, product, qnty);
		if(isSuccess){
			historyController.insertEventRecord(old_event+ "\n <--- has been edited to ---> \n"+new_event);
			Toast.makeText(this,"Update "+input[1]+" success!",Toast.LENGTH_LONG).show();
			onClearAll(v);
		}
		else{
			Toast.makeText(this,"Update "+input[1]+" fail!",Toast.LENGTH_LONG).show();
			//TODO why fail?
		}
      	return isSuccess;
	}
	public void onClearAll(View v){
		((EditText)findViewById(R.id.ProductID)).setText("");
		((EditText)findViewById(R.id.ProductName)).setText("");
		((EditText)findViewById(R.id.BuyPrice)).setText("");
		((EditText)findViewById(R.id.SellPrice)).setText("");
		((EditText)findViewById(R.id.Quantity)).setText("");
		((EditText)findViewById(R.id.Detail)).setText("");
	}
	public void onCancel(View v){
		super.onBackPressed();
		Intent a = new Intent(this,InventoryPage.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,InventoryPage.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
