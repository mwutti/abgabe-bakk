package at.aau.connectapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import at.aau.connectapp.beans.Impulsvortrag;
import at.aau.connectapp.beans.Special;
import at.aau.connectapp.beans.Stand;

public class SpecialDao {
	public static final String TAG = "Special Dao";
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumnsSpecial = { 
			MySQLiteHelper.COLUMN_ID,			
			MySQLiteHelper.COLUMN_BESCHREIBUNG,						
			MySQLiteHelper.COLUMN_STAND_NUMMER
			
			};
	
	private String[] allColumnsStand = {
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NR,
			MySQLiteHelper.COLUMN_X1,
			MySQLiteHelper.COLUMN_X2,
			MySQLiteHelper.COLUMN_Y1,
			MySQLiteHelper.COLUMN_Y2			
	};

	public MySQLiteHelper getDbHelper() {
		return dbHelper;
	}

	public SpecialDao(Context context) {
		dbHelper = new MySQLiteHelper(context);		
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.d(TAG, dbHelper.getDatabaseName());
		
	}

	public void close() {
		dbHelper.close();
	}
	  

	public List<Special> getAllSpecials() {
		List<Special> sp = new ArrayList<Special>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_SPECIALS, allColumnsSpecial, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Special special = cursorToSpecial(cursor);
			sp.add(special);
			cursor.moveToNext();
		}
		
		cursor.close();
		return sp;
	}
	
	
	

	private Special cursorToSpecial(Cursor cursor) {
		Special sp = new Special();
		sp.setId(cursor.getInt(0));			
		sp.setBeschreibung(cursor.getString(1));		
		sp.setStandNR(cursor.getString(2));		
		sp.setStand(findStandByNr(Integer.valueOf(sp.getStandNR())));
		return sp;
	}
	
	private Stand cursorToStand(Cursor cursor){
		Stand stand = new Stand();
		stand.setId(cursor.getInt(0));
		stand.setNr(cursor.getInt(1));
		stand.setX1(cursor.getInt(2));
		stand.setY1(cursor.getInt(3));
		stand.setX2(cursor.getInt(4));
		stand.setY2(cursor.getInt(5));
		return stand;
		
		
	}
	
	public Special findSpecialByID(long id){
		Log.d(TAG,"id: "+ id);
		Special result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_SPECIALS, allColumnsSpecial, "_id like "+ id, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToSpecial(cursor);
		cursor.close();
		
		return result;
	}
	
	public Stand findStandByNr(int nr){
		Stand stand = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_STAND,allColumnsStand, MySQLiteHelper.COLUMN_NR + " like " + nr, null,null,null,null);
		cursor.moveToFirst();
		stand = cursorToStand(cursor);
		cursor.close();
		
		return stand;
	}

}
