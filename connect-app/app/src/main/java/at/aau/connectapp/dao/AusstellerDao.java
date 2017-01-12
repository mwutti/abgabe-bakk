package at.aau.connectapp.dao;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.beans.Stand;

public class AusstellerDao {
	private static String TAG ="AusstellerDao";
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumnsAussteller = { 
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TYPE,		
			MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_BESCHREIBUNG,			
			MySQLiteHelper.COLUMN_ADRESSE,
			MySQLiteHelper.COLUMN_TELEFON,
			MySQLiteHelper.COLUMN_INTERNET,
			MySQLiteHelper.COLUMN_EMAIL,			
			MySQLiteHelper.COLUMN_STAND_NUMMER,
			MySQLiteHelper.COLUMN_LOGO_NAME	,	
			MySQLiteHelper.COLUMN_IS_FAVORITE
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

	public AusstellerDao(Context context) {
		dbHelper = new MySQLiteHelper(context);		
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.d(TAG, dbHelper.getDatabaseName());
		
	}

	public void close() {
		dbHelper.close();
	}
	  

	public List<Aussteller> getAllAussteller() {
		List<Aussteller> aussteller = new ArrayList<Aussteller>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsAussteller, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Aussteller aus = cursorToAussteller(cursor);
			aussteller.add(aus);
			cursor.moveToNext();
		}
		
		cursor.close();
		return aussteller;
	}
	
	public void setFavorite(int value, int id){
		
		ContentValues args = new ContentValues();
	    args.put(MySQLiteHelper.COLUMN_IS_FAVORITE, value);
	    
	    database.update(MySQLiteHelper.TABLE_NAME_AUSSTELLER, args, MySQLiteHelper.COLUMN_ID + "=" + id, null);
	    
	}
	public ArrayList<Aussteller> getFavoriteAussteller(){
		ArrayList<Aussteller> aussteller = new ArrayList<Aussteller>();		
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsAussteller, "is_favorite LIKE 1", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Aussteller aus = cursorToAussteller(cursor);
			aussteller.add(aus);
			cursor.moveToNext();
		}
		
		return aussteller;		
	}

	private Aussteller cursorToAussteller(Cursor cursor) {
		Aussteller aus = new Aussteller();
		aus.setId(cursor.getInt(0));
		aus.setType(cursor.getString(1));
		aus.setName(cursor.getString(2));
		aus.setBeschreibung(cursor.getString(3));
		aus.setAdresse(cursor.getString(4));
		aus.setTelefon(cursor.getString(5));
		aus.setInternet(cursor.getString(6));
		aus.setEmail(cursor.getString(7));
		aus.setStand_nummer(cursor.getString(8));
		aus.setLogo_name(cursor.getString(9));
		aus.setIs_favorite(cursor.getInt(10));
		aus.setStand(findStandByNr(Integer.valueOf(aus.getStand_nummer())));
		return aus;
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
	
	public Aussteller findAusstellerByID(long id){
		Aussteller result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsAussteller, "_id like "+ id, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToAussteller(cursor);
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

	public Aussteller findAusstellerByName(String searchString) {
		Aussteller result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsAussteller, MySQLiteHelper.COLUMN_NAME+ " like " + searchString, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToAussteller(cursor);
		cursor.close();
		
		return result;
	}

	public Aussteller findAusstellerByStand(String nr) {
		Aussteller result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsAussteller, MySQLiteHelper.COLUMN_STAND_NUMMER+ " like " + nr, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() == 0){
			
		}else{
			result = cursorToAussteller(cursor);
		cursor.close();
		}
		
		
		return result;
	}
} 


