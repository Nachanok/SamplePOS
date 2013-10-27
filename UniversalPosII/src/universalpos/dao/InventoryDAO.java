package universalpos.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InventoryDAO extends SQLiteOpenHelper implements DataDAO 
{
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_INVENTORY = "inventory_db";
    
	public InventoryDAO(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " +
	    		TABLE_INVENTORY + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" ProductID TEXT,"+
	    		" ProductName TEXT,"+
	    		" Buy REAL,"+
	    		" Sell REAL,"+
	    		" Quantity INTEGER,"+
	    		" Date TEXT," +
	    		" Detail TEXT)");
	    Log.d("CREATE TABLE INVENTORY","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	
	@Override
	public boolean update(String[] x) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String[] x) 
	{
		/*
		 * x[0]	= ProductID 	<String>
		 * x[1] = ProductName 	<String>
		 * x[2] = Buy 			<double>
		 * x[3] = Sell 			<double>
		 * x[4] = Quantity		<int>
		 * x[5] = Detail 		<String>
		 */
		try 
		 {
			   Date now = new Date();
			   String format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(now);//get date
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase(); // Write Data
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("ProductID", x[0]); 
	    	   Val.put("ProductName", x[1]);
	    	   Val.put("Buy",x[2]);
	    	   Val.put("Sell",x[3]);
	    	   Val.put("Quantity",x[4]);
	    	   Val.put("Date", format);
	    	   Val.put("Detail", x[5]);
	    	   db.insert(TABLE_INVENTORY, null, Val);
			db.close();
			return true; // return rows inserted.
		 } 
		 catch (Exception e) 
		 {
		    return false;
		 }
	}

	@Override
	public String[] findByKey(String x) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
		try {
			String arrData[][] = null;	
			String data[] = null;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); //Read Data
			 String strSQL = "SELECT  * FROM " + TABLE_INVENTORY;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getCount()][cursor.getColumnCount()];
						data = new String[cursor.getCount()];
						int i= 0;
						do {				
							data[i] = "ID:"+cursor.getString(0)+"\tProductID:"+cursor.getString(1)+"\tName:"+cursor.getString(2)+"\tSell:"+cursor.getString(4)+"\tQnty:"+cursor.getString(5);
							//ID:xxx | ItemID:xxx | ItemName:xxx | Sell:xxx | Qnty:xxx
							i++;
						} while (cursor.moveToNext());						

					}
			 	}
			 	cursor.close();
			 		return data;//arrData;
		 } 
		catch (Exception e) 
		 {
		    return null;
		 }
	}

}
