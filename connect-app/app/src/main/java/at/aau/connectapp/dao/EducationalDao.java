package at.aau.connectapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import at.aau.connectapp.beans.Educational;
import at.aau.connectapp.beans.Stand;

public class EducationalDao {
	
private static String TAG ="EducationalDao";
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumnsEducational = { 
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TYPE,		
			MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_BESCHREIBUNG,	
			MySQLiteHelper.COLUMN_PERSON,
			MySQLiteHelper.COLUMN_ADRESSE,
			MySQLiteHelper.COLUMN_TELEFON,
			MySQLiteHelper.COLUMN_INTERNET,
			MySQLiteHelper.COLUMN_EMAIL,
			MySQLiteHelper.COLUMN_STANDORTE,
			MySQLiteHelper.COLUMN_STUDIENGAENGE,
			MySQLiteHelper.COLUMN_ZULASSUNGSVORRAUSSETZUNGEN,
			MySQLiteHelper.COLUMN_ABSCHLUSS,
			MySQLiteHelper.COLUMN_STAND_NUMMER,
			MySQLiteHelper.COLUMN_LOGO_NAME	,	
			MySQLiteHelper.COLUMN_PHOTO_NAME,
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

	public EducationalDao(Context context) {
		dbHelper = new MySQLiteHelper(context);		
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.d(TAG, dbHelper.getDatabaseName());
		
	}

	public void close() {
		dbHelper.close();
	}
	  

//	public List<Educational> getAllEducationals() {
//		List<edusteller> edusteller = new ArrayList<edusteller>();
//
//		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_eduSTELLER, allColumnsedusteller, null, null, null, null, null);
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			edusteller edu = cursorToedusteller(cursor);
//			edusteller.add(edu);
//			cursor.moveToNext();
//		}
//		
//		cursor.close();
//		return edusteller;
//	}
	
//	public void setFavorite(int value, int id){
//		
//		ContentValues args = new ContentValues();
//	    args.put(MySQLiteHelper.COLUMN_IS_FAVORITE, value);
//	    
//	    database.update(MySQLiteHelper.TABLE_NAME_eduSTELLER, args, MySQLiteHelper.COLUMN_ID + "=" + id, null);
//	    
//	}
	
//	public ArrayList<Educational> getFavoriteedusteller(){
//		ArrayList<edusteller> edusteller = new ArrayList<edusteller>();		
//		
//		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_eduSTELLER, allColumnsedusteller, "is_favorite LIKE 1", null, null, null, null);
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			edusteller edu = cursorToedusteller(cursor);
//			edusteller.add(edu);
//			cursor.moveToNext();
//		}
//		
//		return edusteller;		
//	}

	private Educational cursorToEducational(Cursor cursor) {
		Educational edu = new Educational();
		edu.setId(cursor.getInt(0));
		edu.setType(cursor.getString(1));
		edu.setName(cursor.getString(2));
		edu.setBeschreibung(cursor.getString(3));
		edu.setPerson(cursor.getString(4));
		edu.setAdresse(cursor.getString(5));
		edu.setTelefon(cursor.getString(6));
		edu.setInternet(cursor.getString(7));
		edu.setEmail(cursor.getString(8));
		edu.setStandorte(cursor.getString(9));
		edu.setStudiengaenge(cursor.getString(10));
		edu.setZulassung(cursor.getString(11));
		edu.setAbschluss(cursor.getString(12));
		edu.setStand_nummer(cursor.getString(13));
		edu.setLogo_name(cursor.getString(14));
		edu.setPhoto_name(cursor.getString(15));
		edu.setIs_favorite(cursor.getInt(16));
		edu.setStand(findStandByNr(Integer.valueOf(edu.getStand_nummer())));
		return edu;
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
	
	public Educational findEducationalByID(long id){
		Educational result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumnsEducational, "type like \"B\" AND _id like "+ id, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToEducational(cursor);
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
