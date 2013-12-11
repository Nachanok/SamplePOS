package universalpos.activity;
import universalpos.controller.CustomerController;
import universalpos.model.Customer;
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
public class CustomerPage_add extends Activity {
	private CustomerController customerController = new CustomerController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_page_add);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_page_add, menu);
		return true;
	}
	public boolean onInsert(View v){
		boolean isSuccess = false;
		customerController = new CustomerController((Context)this);
		String[] input = new String[6];
		input[0] = ((EditText)findViewById(R.id.CustomerID)).getText().toString();
		input[1] = ((EditText)findViewById(R.id.CustomerName)).getText().toString();
		input[2] = ((EditText)findViewById(R.id.Tel)).getText().toString();
		input[3] = ((EditText)findViewById(R.id.Detail)).getText().toString();
		Customer customer = new Customer(input[0],input[1],input[2],input[3]);
		isSuccess = customerController.insert(customer);
		if(isSuccess){
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
		((EditText)findViewById(R.id.CustomerID)).setText("");
		((EditText)findViewById(R.id.CustomerName)).setText("");
		((EditText)findViewById(R.id.Tel)).setText("");
		((EditText)findViewById(R.id.Detail)).setText("");
	}
	public void onCancel(View v){
		super.onBackPressed();
		Intent a = new Intent(this,CustomerPage.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,CustomerPage.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
