package universalpos.dao;
import java.text.SimpleDateFormat;
import java.util.Date;
import universalpos.model.SaleLineItem;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
@SuppressLint("SimpleDateFormat")
public class EventRecordDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_EVNENT_RECORD = "event_record_db";
  
	public EventRecordDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE " +
	    		TABLE_EVNENT_RECORD + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" Date TEXT," +
	    		" Event TEXT)");
	    Log.d("CREATE TABLE EVENT RECORD","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	public boolean update(){
		    return  false;
	}
	public boolean delete() {
		    return false; 
	}
	@SuppressLint("SimpleDateFormat")
	public boolean insert(String event){
		try {
			   Date now = new Date();
			   String Date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(now);
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase();
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("Date",Date);
	    	   Val.put("Event",event);
	    	   db.insert(TABLE_EVNENT_RECORD, null, Val);
			db.close();
			return true;
		 } 
		 catch (Exception e) {
		    return false;
		 }
	}
	//TODO make findByKey specify event record
	public SaleLineItem findByKey(String productID) {
		return null;
	}
	//TODO make findAll specify event record
	public SaleLineItem[] findAll() 	{
		return null;
	}
}
