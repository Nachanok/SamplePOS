package universalpos.dao;

import android.content.Context;

public class DAOFactory {
	private Context context; 
	public DAOFactory(Context context){
		this.context = context;
	}
	public InventoryDAO getInventoryDAO(){
			return new InventoryDAO(context);
	}
	public SaleRecordDAO getSaleRecordDAO(){
		return new SaleRecordDAO(context);
	}
		public EventRecordDAO getEventRecordDAO(){
		return new EventRecordDAO(context);
	}
	public CustomerDAO getCustomerDAO(){
		return new CustomerDAO(context);
	}
}
