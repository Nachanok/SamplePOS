package universalpos.model.adapter;
import java.util.ArrayList;

import universalpos.model.EventRecord;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.universalposii.R;
public class AdapterListViewDataEventRecord extends BaseAdapter{
	private LayoutInflater mInflater;
    private Context context;
    private ArrayList<EventRecord> listData = new ArrayList<EventRecord>();
    public AdapterListViewDataEventRecord(Context context,ArrayList<EventRecord> listData) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listData = listData;
    }
	@Override
	public int getCount() {
		return listData.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		HolderListAdapterEventRecord holderListAdapterEventRecord;
        if(convertView == null)
        {
            //Use own custom layout
            convertView = mInflater.inflate(R.layout.adapter_listview_event_record, null);
             //Create list for each line item
            holderListAdapterEventRecord = new HolderListAdapterEventRecord();
            //Link it with list view
            holderListAdapterEventRecord.textDate = (TextView) convertView.findViewById(R.id.textDate);
            holderListAdapterEventRecord.textEvent = (TextView) convertView.findViewById(R.id.textEvent);
            convertView.setTag(holderListAdapterEventRecord);
        }else{
        	holderListAdapterEventRecord = (HolderListAdapterEventRecord) convertView.getTag();
        }
        //Display data position by position
        holderListAdapterEventRecord.textDate.setText("Date : "+listData.get(position).getTime());
        holderListAdapterEventRecord.textEvent.setText("Event : "+ listData.get(position).getEvent());
        //ถ้าทำการเลือกที่ List จะแสดงข้อความ ว่าทำการเลือกที่ List ที่เท่าไร
        return convertView;
	}
}
