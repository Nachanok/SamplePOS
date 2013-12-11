package universalpos.activity;
import java.util.ArrayList;
import universalpos.controller.HistoryController;
import universalpos.model.SaleRecord;
import universalpos.model.adapter.AdapterListViewDataSaleRecode;
import com.example.universalposii.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
public class SaleRecordPage extends Activity {
	 private AdapterListViewDataSaleRecode adapterListViewDataSaleRecord;
	 private ArrayList<SaleRecord> listData = new ArrayList<SaleRecord>();
	 private ListView listViewData;
	 private LinearLayout itemLayout; 
	 private AlertDialog.Builder alertDialog_Del;
	 private HistoryController historyController;
	 private SaleRecord[] saleRecords = null;
	 public SaleRecordPage() {
		historyController= new HistoryController(this);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_sale_record_page);
       listViewData = (ListView)findViewById(R.id.listView1);
       saleRecords = historyController.findAllSaleRecord();
       if(saleRecords!= null)
       {
       	for(int i=0;i<saleRecords.length;i++)
       		listData.add(saleRecords[i]); 
       	adapterListViewDataSaleRecord = new AdapterListViewDataSaleRecode(getBaseContext(),listData);
       	listViewData.setAdapter(adapterListViewDataSaleRecord);
       }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sale_record_page, menu);
		return true;
	}	
	public void onRefresh(){
		listData.clear();
		saleRecords = historyController.findAllSaleRecord();
		if(saleRecords!= null){
			for(int i=0;i<saleRecords.length;i++)
				listData.add(saleRecords[i]); 
			adapterListViewDataSaleRecord = new AdapterListViewDataSaleRecode(getBaseContext(),listData);
			listViewData.setAdapter(adapterListViewDataSaleRecord);
		}
		adapterListViewDataSaleRecord.notifyDataSetChanged();
	}
	public void onDeleteAll(View v)
	{
		alertDialog_Del = new AlertDialog.Builder(SaleRecordPage.this);
		alertDialog_Del.setTitle("Confirm Delete ALL!...");
		alertDialog_Del.setMessage("CAUTION ALL SALE RECORD WILL BE DELETE .");
		alertDialog_Del.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				historyController.deleteAllSaleRecord();
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
