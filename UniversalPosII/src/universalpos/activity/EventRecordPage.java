package universalpos.activity;

import com.example.universalposii.R;
import com.example.universalposii.R.layout;
import com.example.universalposii.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EventRecordPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_record_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_record_page, menu);
		return true;
	}

}
