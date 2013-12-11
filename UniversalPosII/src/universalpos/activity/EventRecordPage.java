package universalpos.activity;

import java.util.ArrayList;

import universalpos.controller.CustomerController;
import universalpos.controller.HistoryController;
import universalpos.model.Customer;
import universalpos.model.EventRecord;
import universalpos.model.adapter.AdapterListViewDataCustomer;
import universalpos.model.adapter.AdapterListViewDataEventRecord;

import com.example.universalposii.R;
import com.example.universalposii.R.layout;
import com.example.universalposii.R.menu;

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

public class EventRecordPage extends Activity {
	 private AdapterListViewDataEventRecord adapterListViewDataEventRecord;
	 private ArrayList<EventRecord> listData = new ArrayList<EventRecord>();
	 private ListView listViewData;
	 private LinearLayout itemLayout; 
	 private AlertDialog.Builder alertDialog_Del;
	 private HistoryController historyController;
	 private EventRecord[] eventRecords = null;
	 public EventRecordPage() {
		historyController= new HistoryController(this);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_record_page);
        listViewData = (ListView)findViewById(R.id.listView1);
        eventRecords = historyController.findAllEventRecord();
        if(eventRecords!= null)
        {
        	for(int i=0;i<eventRecords.length;i++)
        		listData.add(eventRecords[i]); 
        	adapterListViewDataEventRecord = new AdapterListViewDataEventRecord(getBaseContext(),listData);
        	listViewData.setAdapter(adapterListViewDataEventRecord);
        }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.event_record_page, menu);
		return true;
	}	
	public void onRefresh(){
		listData.clear();
		eventRecords = historyController.findAllEventRecord();
		if(eventRecords!= null){
			for(int i=0;i<eventRecords.length;i++)
				listData.add(eventRecords[i]); 
			adapterListViewDataEventRecord = new AdapterListViewDataEventRecord(getBaseContext(),listData);
			listViewData.setAdapter(adapterListViewDataEventRecord);
		}
		adapterListViewDataEventRecord.notifyDataSetChanged();
	}
	public void onDeleteAll(View v)
	{
		alertDialog_Del = new AlertDialog.Builder(EventRecordPage.this);
		alertDialog_Del.setTitle("Confirm Delete ALL!...");
		alertDialog_Del.setMessage("CAUTION ALL EVENT RECORD WILL BE DELETE .");
		alertDialog_Del.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				historyController.deleteAllEventRecord();
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
