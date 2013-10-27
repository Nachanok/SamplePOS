package universalpos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CustomerDAO extends SQLiteOpenHelper implements DataDAO
{

	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_CUSTOMER = "customer_db";
    
	public CustomerDAO(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " +
	    		TABLE_CUSTOMER + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" CustomerID INTEGER,"+
	    		" CustomerName TEXT,"+
	    		" Address TEXT,"+
	    		" Tel TEXT,"+
	    		" TotalBuy REAL,"+
	    		" Detail TEXT)");
	    Log.d("CREATE TABLE CUSTOMER","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	@Override
	public boolean update(String[] x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String[] x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] findByKey(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
