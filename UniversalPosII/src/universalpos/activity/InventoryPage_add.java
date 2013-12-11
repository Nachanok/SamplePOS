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
public class InventoryPage_add extends Activity{
	private InventoryController inventoryController = new InventoryController(this);
	private HistoryController historyController = new HistoryController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_page_add);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.inventory_page_add, menu);
		return true;
	}
	public boolean onInsert(View v){
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
		Product product = new Product(input);
		if(!input[4].equals(""))
		qnty = Integer.parseInt(input[4]);
		isSuccess = inventoryController.insert(product,qnty);
		if(isSuccess){
			historyController.insertEventRecord("\'"+input[4]+"\' ea of \'"+input[1]+"\' was added.");
			Toast.makeText(this,"Add "+input[1]+" success!",Toast.LENGTH_LONG).show();
			onClearAll(v);
		}
		else{
			Toast.makeText(this,"Add "+input[1]+" fail!",Toast.LENGTH_LONG).show();
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
