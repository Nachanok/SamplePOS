package universalpos.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import universalpos.model.Product;
import universalpos.model.SaleLineItem;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class InventoryDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_INVENTORY = "inventory_db";
  
	public InventoryDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
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
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	public boolean update(int id,Product product,int qnty){
		try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
	    		Date now = new Date();
	    		String Date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(now);
	    		ContentValues Val = new ContentValues();
	    		Val.put("ProductID",product.getProductID()); 
	    		Val.put("ProductName",product.getProductName());
	    		Val.put("Buy",product.getCost());
	    		Val.put("Sell",product.getPrice());
	    		Val.put("Quantity",qnty);
	    		Val.put("Date",Date);
	    		Val.put("Detail",product.getProductDetail());
	    		db.update(TABLE_INVENTORY, Val, " ID = ?",
	                   new String[] { String.valueOf(id) });
			db.close();
			return true; // return rows updated.
		 } catch (Exception e) {
		    return  false;
		 }
	}
	public boolean delete(String productID) {
		try{
			SQLiteDatabase db;
	    	db = this.getWritableDatabase();// Write Data permission	    		
	    	db.delete(TABLE_INVENTORY, "ProductID = ?",new String[] { String.valueOf(productID) });
			db.close();
			return true;
		 }
		 catch (Exception e) {
		    return false; 
		 }
	}
	@SuppressLint("SimpleDateFormat")
	public boolean insert(Product product,int qnty){
		try {
			   Date now = new Date();
			   String Date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(now);
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase();
	    	   ContentValues Val = new ContentValues();
	    	   String autoIncretement = null;
	    	   Val.put("ID",autoIncretement);
	    	   Val.put("ProductID",product.getProductID()); 
	    	   Val.put("ProductName",product.getProductName());
	    	   Val.put("Buy",product.getCost());
	    	   Val.put("Sell",product.getPrice());
	    	   Val.put("Quantity",qnty);
	    	   Val.put("Date",Date);
	    	   Val.put("Detail",product.getProductDetail());
	    	   db.insert(TABLE_INVENTORY, null, Val);
			db.close();
			return true;
		 } 
		 catch (Exception e) {
		    return false;
		 }
	}
	public SaleLineItem findByKey(String productID) {
		try {
			String arrData[] = null;
			SQLiteDatabase db;
			db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_INVENTORY, new String[] { "*" }, 
					 	"ProductID=?",
			            new String[] { String.valueOf(productID) }, null, null, null, null);
			 	if(cursor != null){
					if (cursor.moveToFirst()){
						arrData = new String[cursor.getColumnCount()];
							for(int i = 0;i<cursor.getColumnCount();i++)
								arrData[i] = cursor.getString(i);
					}
			 	}
			 	cursor.close();
				db.close();
				Product product = new Product();
				product.setId(Integer.parseInt(arrData[0]));
				product.setProductID(arrData[1]);
				product.setProductName(arrData[2]);
				product.setCost(Double.parseDouble(arrData[3]));
				product.setPrice(Double.parseDouble(arrData[4]));
				product.setProductDetail(arrData[7]);
				SaleLineItem saleLineItem = new SaleLineItem(product, Integer.parseInt(arrData[5]));
				return saleLineItem;
		 } 
		catch (Exception e) {
		    return null;
		}
	}
	public SaleLineItem[] findAll() 
	{
		try {
			String arrData[] = null;
			SaleLineItem[] saleLineItems = null;
			SaleLineItem saleLineItem = null;
			Product product;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase();
			 String strSQL = "SELECT  * FROM " + TABLE_INVENTORY +" ORDER BY ProductName";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst())
					{
						arrData = new String[cursor.getColumnCount()];
						saleLineItems = new SaleLineItem[cursor.getCount()];
						int i= 0;
						do 
						{				
							for(int j = 0;j<cursor.getColumnCount();j++)
							{
								arrData[j] = cursor.getString(j);
							}
							product = new Product();
							product.setId(Integer.parseInt(arrData[0]));
							product.setProductID(arrData[1]);
							product.setProductName(arrData[2]);
							product.setCost(Double.parseDouble(arrData[3]));
							product.setPrice(Double.parseDouble(arrData[4]));
							product.setProductDetail(arrData[7]);
							saleLineItem = new SaleLineItem(product, Integer.parseInt(arrData[5]));
							saleLineItems[i] = saleLineItem;
							i++;
						} 
						while (cursor.moveToNext());						
					}
			 	}
			 	cursor.close();
			 	return saleLineItems;
		 } 
		catch (Exception e) 
		 {
		    return null;
		 }
	}

}
