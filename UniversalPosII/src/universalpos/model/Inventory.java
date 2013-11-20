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
	
	public boolean delete(String id) 
	{	
		return dataDao.delete(id);
	}
	
	public boolean insert(String[] x)
	{
		// TODO add product to product catalog
		if(x[0].equals("") || x[1].equals("") || x[2].equals("") || x[3].equals(""))//ProductID,ProductName,BuyPrice,SellPrice can't be NULL
		{
			return false;
		}
		if(x[5].equals(""))
			x[5]="N/A";
		if(Integer.parseInt(x[4])<=0 || x[4].equals(""))//quantity can't less than 0
			x[4]="1";
		boolean isSuccess = dataDao.insert(x);
		return isSuccess;
	}

	public String[] findByKey(String x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[] findAll() 
	{
		/*
		 * [x][0] = ID 			<int>
		 * [x][1] = ProductID 	<String>
		 * [x][2] = ProductName <String>
		 * [x][3] = Buy 		<double>
		 * [x][4] = Sell		<double>
		 * [x][5] = Quantity 	<int>
		 * [x][6] = Date 		<String>
		 * [x][7] = Detail 		<String>
		 */
		String[][] list_2d = dataDao.findAll();
		if(list_2d!=null)
		{
			String[] list_1d = new String[list_2d.length];
			for(int i = 0;i< list_2d.length;i++)
			{
				list_1d[i] = list_2d[i][2]+" "+list_2d[i][4]+" "+list_2d[i][5];
			}
			return list_1d;
		}
		else
			return new String[]{"No item here!"};				
	}
}
