package universalpos.model.adapter;
import java.util.ArrayList;
import universalpos.model.SaleRecord;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.universalposii.R;

public class AdapterListViewDataSaleRecode extends BaseAdapter{
	private LayoutInflater mInflater;
    private Context context;
    private ArrayList<SaleRecord> listData = new ArrayList<SaleRecord>();
    public AdapterListViewDataSaleRecode(Context context,ArrayList<SaleRecord> listData) {
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
		HolderListAdapterSaleRecord holderListAdapterSaleRecord;
        if(convertView == null)
        {
            //Use own custom layout
            convertView = mInflater.inflate(R.layout.adapter_listview_sale_record, null);
             //Create list for each line item
            holderListAdapterSaleRecord = new HolderListAdapterSaleRecord();
            //Link it with list view
            holderListAdapterSaleRecord.textDate = (TextView) convertView.findViewById(R.id.textDate);
            holderListAdapterSaleRecord.textCustomer = (TextView) convertView.findViewById(R.id.textCustomer);
            holderListAdapterSaleRecord.textItemList = (TextView) convertView.findViewById(R.id.textItemList);
            holderListAdapterSaleRecord.textEffect = (TextView) convertView.findViewById(R.id.textEffect);
            convertView.setTag(holderListAdapterSaleRecord);
        }else{
        	holderListAdapterSaleRecord = (HolderListAdapterSaleRecord) convertView.getTag();
        }
        //Display data position by position
        holderListAdapterSaleRecord.textDate.setText("Date : "+listData.get(position).getTime());
        holderListAdapterSaleRecord.textCustomer.setText("CustomerID : "+ listData.get(position).getCustomerID()+"\nCustomerName : "+listData.get(position).getCustomerName());
        holderListAdapterSaleRecord.textItemList.setText("Product : \n"+ listData.get(position).getItemsList());
        holderListAdapterSaleRecord.textEffect.setText("Summation : "+ listData.get(position).getEffect()+" .-");
        //ถ้าทำการเลือกที่ List จะแสดงข้อความ ว่าทำการเลือกที่ List ที่เท่าไร
        return convertView;
	}
}
