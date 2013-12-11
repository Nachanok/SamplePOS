package universalpos.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import universalpos.model.EventRecord;
import universalpos.model.Product;
import universalpos.model.SaleRecord;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SaleRecordDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabase_sale_record";
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
	    		" Day INTEGER," +
	    		" Month INTEGER," +
	    		" Year INTEGER," +
	    		" Hour INTEGER," +
	    		" Min INTEGER," +
	    		" ItemsName TEXT,"+
	    		" CustomerID INTEGER,"+
	    		" CustomerName TEXT,"+
	    		" Effect REAL,"+
	    		" Detail TEXT)");
	    Log.d("CREATE TABLE SALE RECORD","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
	}
	public boolean deleteAll() {
		return false;
	}
	@SuppressLint("SimpleDateFormat")
	public boolean insert(SaleRecord saleRecord) {
		try {
			System.out.println("DEBUG");
			   Date now = new Date();
			   String Date = new SimpleDateFormat("d MM yyyy HH mm").format(now);
			   String[] split_date = Date.split(" ");
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase();
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("Day", Integer.parseInt(split_date[0]));
	    	   Val.put("Month",Integer.parseInt( split_date[1]));
	    	   Val.put("Year", Integer.parseInt(split_date[2]));
	    	   Val.put("Hour", Integer.parseInt(split_date[3]));
	    	   Val.put("Min", Integer.parseInt(split_date[4]));
	    	   Val.put("ItemsName",saleRecord.getItemsList());
	    	   Val.put("CustomerID",saleRecord.getCustomerID());
	    	   Val.put("CustomerName",saleRecord.getCustomerName());
	    	   Val.put("Effect",saleRecord.getEffect());
	    	   Val.put("Detail",saleRecord.getDetail());
	    	   db.insert(TABLE_SALE_RECORD, null, Val);
			db.close();
			return true;
		 } 
		 catch (Exception e) {
		    return false;
		 }
	}
	public SaleRecord[] findAll() {
		try {
			String arrData[] = null;
			SaleRecord[] saleRecords = null;
			SaleRecord saleRecord;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase();
			 String strSQL = "SELECT  * FROM " + TABLE_SALE_RECORD + " ORDER BY Year,Month,Day,Hour,Min";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst())
					{
						arrData = new String[cursor.getColumnCount()];
						saleRecords = new SaleRecord[cursor.getCount()];
						int i= 0;
						do 
						{				
							for(int j = 0;j<cursor.getColumnCount();j++){
								arrData[j] = cursor.getString(j);
							}
							saleRecord = new SaleRecord();
							saleRecord.setId(Integer.parseInt(arrData[0]));
							saleRecord.setDay(Integer.parseInt(arrData[1]));
							saleRecord.setMonth(Integer.parseInt(arrData[2]));
							saleRecord.setYear(Integer.parseInt(arrData[3]));
							saleRecord.setHour(Integer.parseInt(arrData[4]));
							saleRecord.setMin(Integer.parseInt(arrData[5]));
							saleRecord.setItemsList(arrData[6]);
							saleRecord.setCustomerID(arrData[7]);
							saleRecord.setCustomerName(arrData[8]);
							saleRecord.setEffect(arrData[9]);
							saleRecord.setDetail(arrData[10]);
							saleRecords[i] = saleRecord;
							i++;
						} 
						while (cursor.moveToNext());						
					}
			 	}
			 	cursor.close();
			 	return saleRecords;
		 } 
		catch (Exception e) 
		 {
		    return null;
		 }
	}
	
}
