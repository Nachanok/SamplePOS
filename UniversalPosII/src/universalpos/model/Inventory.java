package universalpos.model;

import universalpos.dao.DAOFactory;
import universalpos.dao.DataDAO;
import android.content.Context;

public class Inventory 
{
	private DataDAO dataDao;
	private DAOFactory daoFac;
	public Inventory(Context context) 
	{
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
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean insert(String[] x)
	{
		// TODO Auto-generated method stub
		return false;
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
