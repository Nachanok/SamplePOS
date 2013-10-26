package universalpos.dao;

public class DAOFactory 
{
	private DataDAO getDAO(String type)
	{
		if(type.equalsIgnoreCase("history"))
			return new HistoryDAO();
		else if(type.equalsIgnoreCase("customer"))
			return new CustomerDAO();
		else if(type.equalsIgnoreCase("inventory"))
			return new InventoryDAO();
		else
			return null;
	}
}
