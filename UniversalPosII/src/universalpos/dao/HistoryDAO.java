package universalpos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HistoryDAO extends SQLiteOpenHelper implements DataDAO
{
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_HISTORY = "history_db";
    
	public HistoryDAO(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " +
	    		TABLE_HISTORY + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" ItemID INTEGER,"+
	    		" ItemName TEXT,"+
	    		" CustomerID INTEGER,"+
	    		" CustomerName TEXT,"+
	    		" Event TEXT,"+
	    		" Buy REAL,"+
	    		" Sell REAL,"+
	    		" Quantity INTEGER,"+
	    		" Total REAL,"+
	    		" Date TEXT," +
	    		" Detail TEXT)");
	    Log.d("CREATE TABLE HISTORY","Create Table Successfully.");
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
	public boolean delete(String id) {
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
	public String[][] findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
