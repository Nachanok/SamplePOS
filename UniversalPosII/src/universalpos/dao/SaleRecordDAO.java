package universalpos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SaleRecordDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_SALE_RECORD = "sale_record_db";
    
	public SaleRecordDAO(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " +
	    		TABLE_SALE_RECORD + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" Date TEXT," +
	    		" ItemID INTEGER,"+
	    		" ItemName TEXT,"+
	    		" Quantity INTEGER,"+
	    		" Status TEXT,"+
	    		" Price REAL,"+
	    		" CustomerID INTEGER,"+
	    		" CustomerName TEXT,"+
	    		" TotalPrice REAL,"+
	    		" Detail TEXT)");
	    Log.d("CREATE TABLE SALE RECORD","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
	}
	public boolean update(String[] x) {
		return false;
	}
	public boolean delete(String id) {
		return false;
	}
	public boolean insert(String[] x) {
		// TODO make insert specify to sale record
		return false;
	}
	public String[] findByKey(String x) {
		return null;
	}
	public String[][] findAll() {
		return null;
	}
}
