package universalpos.dao;

import android.content.Context;

public class DAOFactory 
{
	private Context context; 
	public DAOFactory(Context context)
	{
		this.context = context;
	}
	private DataDAO getDAO(String type)
	{
		if(type.equalsIgnoreCase("history"))
			return new HistoryDAO(context);
		else if(type.equalsIgnoreCase("customer"))
			return new CustomerDAO(context);
		else if(type.equalsIgnoreCase("inventory"))
			return new InventoryDAO(context);
		else
			return null;
	}
}
