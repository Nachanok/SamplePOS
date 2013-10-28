package universalpos.model;
import universalpos.activity.InventoryPage;
import universalpos.activity.InventoryPage_add;
import universalpos.dao.DAOFactory;
import universalpos.dao.DataDAO;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Inventory 
{
	private Context context;
	private DataDAO dataDao;
	private DAOFactory daoFac;
	public Inventory(Context context) 
	{
		this.context = context;
		daoFac  = new DAOFactory(context);
		dataDao = daoFac.getDAO("inventory");
	}
	public boolean update(String[] x) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean delete(int id) 
	{	
		return dataDao.delete(id);
	}
	
	public boolean insert(String[] x)
	{
		if(x[0].equals("") || x[1].equals("") || x[2].equals("") || x[3].equals(""))//ProductID,ProductName,BuyPrice,SellPrice can't be NULL
		{
        	 //Toast.makeText(context,"Insert Data Failed, ProductID,ProductName,BuyPrice,SellPrice can't be NULL",Toast.LENGTH_LONG).show();
			return false;
		}
		if(x[5].equals(""))
			x[5]="N/A";
		if(Integer.parseInt(x[4])<=0 || x[4].equals(""))//quantity can't less than 0
			x[4]="1";
		boolean isSuccess = dataDao.insert(x);
		
//      	if(isSuccess)
//      	{
//      		 Toast.makeText(context,"Insert Data Successfully",Toast.LENGTH_LONG).show(); 
//      	}
//      	else
//      	{
//         	 Toast.makeText(context,"Insert Data Failed.",Toast.LENGTH_LONG).show(); 
//      	}
     
		return isSuccess;
	}

	public String[] findByKey(String x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[] findAll() 
	{
		if(dataDao.findAll()!=null)
			return dataDao.findAll();
		else
			return  new String[]{"No item here!"};
				
	}
}
