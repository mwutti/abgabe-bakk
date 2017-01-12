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
import at.aau.connectapp.beans.Impulsvortrag;
import at.aau.connectapp.beans.Stand;

public class ImpulsvortraegeDao {
private static String TAG ="ImpulsvortraegeDao";
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumnsImpulsvortraege = { 
			MySQLiteHelper.COLUMN_ID,					
			MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_BESCHREIBUNG,	
			MySQLiteHelper.COLUMN_VON,
			MySQLiteHelper.COLUMN_BIS,			
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

	public ImpulsvortraegeDao(Context context) {
		dbHelper = new MySQLiteHelper(context);		
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.d(TAG, dbHelper.getDatabaseName());
		
	}

	public void close() {
		dbHelper.close();
	}
	  

	public List<Impulsvortrag> getAllImpulsvortraege() {
		List<Impulsvortrag> imp = new ArrayList<Impulsvortrag>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_IMPULSVORTRAEGE, allColumnsImpulsvortraege, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Impulsvortrag impuls = cursorToImpulsvortrag(cursor);
			imp.add(impuls);
			cursor.moveToNext();
		}
		
		cursor.close();
		return imp;
	}
	
	
	

	private Impulsvortrag cursorToImpulsvortrag(Cursor cursor) {
		Impulsvortrag imp = new Impulsvortrag();
		imp.setId(cursor.getInt(0));		
		imp.setName(cursor.getString(1));
		imp.setBeschreibung(cursor.getString(2));
		imp.setVon(cursor.getString(3));
		imp.setBis(cursor.getString(4));
		imp.setStandNr(cursor.getString(5));		
		imp.setStand(findStandByNr(Integer.valueOf(imp.getStandNr())));
		return imp;
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
	
	public Impulsvortrag findImpulsvortragByID(long id){
		Log.d(TAG,"id: "+ id);
		Impulsvortrag result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_IMPULSVORTRAEGE, allColumnsImpulsvortraege, "_id like "+ id, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToImpulsvortrag(cursor);
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
