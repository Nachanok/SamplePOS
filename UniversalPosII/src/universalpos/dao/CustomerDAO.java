package universalpos.dao;
import universalpos.model.Customer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class CustomerDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabases_customer";
    private static final String TABLE_CUSTOMER = "customer_db";
	public CustomerDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE " +
	    		TABLE_CUSTOMER + 
	    		"(ID INTEGER PRIMARY KEY ," +
	    		" CustomerID TEXT,"+
	    		" CustomerName TEXT,"+
	    		" Tel TEXT,"+
	    		" Detail TEXT);");
	    Log.d("CREATE TABLE CUSTOMER","Create Table Successfully.");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
	}
	public boolean update(int id,Customer customer) {
		try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
	    		ContentValues Val = new ContentValues();
		    	   Val.put("CustomerID",customer.getCustomerID()); 
		    	   Val.put("CustomerName",customer.getCostomerName());
		    	   Val.put("Tel",customer.getCustomerTel());
		    	   Val.put("Detail",customer.getCustomerDetail());
	    		db.update(TABLE_CUSTOMER, Val, " ID = ?",
	                   new String[] { String.valueOf(id) });
			db.close();
			return true;
		 } catch (Exception e) {
		    return  false;
		 }
	}
	public boolean delete(String CustomerID) {
		try{
			SQLiteDatabase db;
	    	db = this.getWritableDatabase();// Write Data permission	    		
	    	db.delete(TABLE_CUSTOMER, "CustomerID = ?",new String[] { String.valueOf(CustomerID) });
			db.close();
			return true;
		 }
		 catch (Exception e) {
		    return false; 
		 }
	}
	public boolean insert(Customer customer) {
		try {
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase();
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("CustomerID",customer.getCustomerID()); 
	    	   Val.put("CustomerName",customer.getCostomerName());
	    	   Val.put("Tel",customer.getCustomerTel());
	    	   Val.put("Detail",customer.getCustomerDetail());
	    	   db.insert(TABLE_CUSTOMER, null, Val);
			db.close();
			return true;
		 } 
		 catch (Exception e) {
		    return false;
		 }
	}
	public Customer findByKey(String customerID) {
		try {
			String arrData[] = null;
			SQLiteDatabase db;
			db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_CUSTOMER, new String[] { "*" }, 
					 	"CustomerID=?",
			            new String[] { String.valueOf(customerID) }, null, null, null, null);
			 	if(cursor != null){
					if (cursor.moveToFirst()){
						arrData = new String[cursor.getColumnCount()];
							for(int i = 0;i<cursor.getColumnCount();i++)
								arrData[i] = cursor.getString(i);
					}
			 	}
			 	cursor.close();
				db.close();
				Customer customer = new Customer();
				customer.setID(Integer.parseInt(arrData[0]));
				customer.setCustomerID(arrData[1]);
				customer.setCostomerName(arrData[2]);
				customer.setCustomerTel(arrData[3]);
				customer.setCustomerDetail(arrData[4]);
				return customer;
		 } 
		catch (Exception e) {
		    return null;
		}
	}
	public Customer[] findAll() {
		try {
			String arrData[] = null;
			Customer[] customers = null;
			Customer customer;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase();
			 String strSQL = "SELECT  * FROM " + TABLE_CUSTOMER + " ORDER BY CustomerName";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst())
					{
						arrData = new String[cursor.getColumnCount()];
						customers = new Customer[cursor.getCount()];
						int i= 0;
						do 
						{				
							for(int j = 0;j<cursor.getColumnCount();j++){
								arrData[j] = cursor.getString(j);
							}
							customer = new Customer();
							customer.setID(Integer.parseInt(arrData[0]));
							customer.setCustomerID(arrData[1]);
							customer.setCostomerName(arrData[2]);
							customer.setCustomerTel(arrData[3]);
							customer.setCustomerDetail(arrData[4]);
							customers[i] = customer;
							i++;
						} 
						while (cursor.moveToNext());						
					}
			 	}
			 	cursor.close();
			 	return customers;
		 } 
		catch (Exception e) 
		 {
		    return null;
		 }
	}
}
