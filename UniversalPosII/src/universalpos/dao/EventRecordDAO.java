package universalpos.dao;
import java.text.SimpleDateFormat;
import java.util.Date;
import universalpos.model.EventRecord;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
@SuppressLint("SimpleDateFormat")
public class EventRecordDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabases_event_record";
    private static final String TABLE_EVNENT_RECORD = "event_record_db";
  
	public EventRecordDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE " +
	    		TABLE_EVNENT_RECORD + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" Day INTEGER," +
	    		" Month INTEGER," +
	    		" Year INTEGER," +
	    		" Hour INTEGER," +
	    		" Min INTEGER," +
	    		" Event TEXT)");
	    Log.d("CREATE TABLE EVENT RECORD","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	public boolean deleteAll() {
//		DELETE* FROM Customers
//		WHERE 1

		    return false; 
	}
	@SuppressLint("SimpleDateFormat")
	public boolean insert(String event){
		try {
			   Date now = new Date();
			   String Date = new SimpleDateFormat("d MM yyyy HH mm").format(now);
			   String[] split_date = Date.split(" ");
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase();
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("Day", Integer.parseInt(split_date[0]));
	    	   Val.put("Month",Integer.parseInt(split_date[1]));
	    	   Val.put("Year", Integer.parseInt(split_date[2]));
	    	   Val.put("Hour", Integer.parseInt(split_date[3]));
	    	   Val.put("Min", Integer.parseInt(split_date[4]));
	    	   Val.put("Event",event);
	    	   db.insert(TABLE_EVNENT_RECORD, null, Val);
			db.close();
			return true;
		 } 
		 catch (Exception e) {
		    return false;
		 }
	}
	public EventRecord[] findAll() 	{
		try {
			String arrData[] = null;
			EventRecord[] eventRecords = null;
			EventRecord eventRecord;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase();
			 String strSQL = "SELECT  * FROM " + TABLE_EVNENT_RECORD + " ORDER BY Year,Month,Day,Hour,Min";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst())
					{
						arrData = new String[cursor.getColumnCount()];
						eventRecords = new EventRecord[cursor.getCount()];
						int i= 0;
						do 
						{				
							for(int j = 0;j<cursor.getColumnCount();j++){
								arrData[j] = cursor.getString(j);
							}
							eventRecord = new EventRecord();
							eventRecord.setId(Integer.parseInt(arrData[0]));
							eventRecord.setDay(Integer.parseInt(arrData[1]));
							eventRecord.setMonth(Integer.parseInt(arrData[2]));
							eventRecord.setYear(Integer.parseInt(arrData[3]));
							eventRecord.setHour(Integer.parseInt(arrData[4]));
							eventRecord.setMin(Integer.parseInt(arrData[5]));
							eventRecord.setEvent(arrData[6]);
							eventRecords[i] = eventRecord;
							i++;
						} 
						while (cursor.moveToNext());						
					}
			 	}
			 	cursor.close();
			 	return eventRecords;
		 } 
		catch (Exception e) 
		 {
		    return null;
		 }
	}
}
