package universalpos.model;

import java.util.ArrayList;

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

public class AdapterListViewData extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<SaleLineItem> listData = new ArrayList<SaleLineItem>();
    public AdapterListViewData(Context context,ArrayList<SaleLineItem> listData) {
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
        HolderListAdapter holderListAdapter;
        if(convertView == null)
        {
            //Use own custom layout
            convertView = mInflater.inflate(R.layout.adapter_listview, null);
             //Create list for each line item
            holderListAdapter = new HolderListAdapter();
            //Link it with list view
            holderListAdapter.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holderListAdapter.txtDetail = (TextView) convertView.findViewById(R.id.txtDetail);
            holderListAdapter.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holderListAdapter);
        }else{
            holderListAdapter = (HolderListAdapter) convertView.getTag();
        }
        //Display data position by position
        holderListAdapter.txtTitle.setText(listData.get(position).getProduct().getProductName());
        holderListAdapter.txtDetail.setText(listData.get(position).getQnty()+"");
        //Display which check box that check
        holderListAdapter.checkBox.setOnClickListener(new OnClickListener() {
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
