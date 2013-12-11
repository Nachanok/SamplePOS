package universalpos.model.adapter;
import java.util.ArrayList;

import universalpos.model.Customer;

import com.example.universalposii.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterListViewDataCustomer extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Customer> listData = new ArrayList<Customer>();
    public AdapterListViewDataCustomer(Context context,ArrayList<Customer> listData) {
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
		HolderListAdapterCustomer holderListAdapterCustomer;
        if(convertView == null)
        {
            //Use own custom layout
            convertView = mInflater.inflate(R.layout.adapter_listview_customer, null);
             //Create list for each line item
            holderListAdapterCustomer = new HolderListAdapterCustomer();
            //Link it with list view
            holderListAdapterCustomer.textCustomerID = (TextView) convertView.findViewById(R.id.textCustomerID);
            holderListAdapterCustomer.textCustomerName = (TextView) convertView.findViewById(R.id.textCustomerName);
            holderListAdapterCustomer.textCustomerTel = (TextView) convertView.findViewById(R.id.textCustomerTel);
            holderListAdapterCustomer.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holderListAdapterCustomer);
        }else{
        	holderListAdapterCustomer = (HolderListAdapterCustomer) convertView.getTag();
        }
        //Display data position by position
        holderListAdapterCustomer.textCustomerID.setText("ID : "+listData.get(position).getCustomerID());
        holderListAdapterCustomer.textCustomerName.setText("Name : "+ listData.get(position).getCostomerName());
        holderListAdapterCustomer.textCustomerTel.setText("Tel : "+listData.get(position).getCustomerTel());
        //Display which check box that check
        holderListAdapterCustomer.checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context,"CheckBox "+ position +" check!!",Toast.LENGTH_SHORT).show();
            }
        });
        //ถ้าทำการเลือกที่ List จะแสดงข้อความ ว่าทำการเลือกที่ List ที่เท่าไร
        convertView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context,"List "+ position +" click!!",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
	}
}
