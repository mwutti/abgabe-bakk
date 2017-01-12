package at.aau.connectapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import at.aau.connectapp.beans.Company;

public class CompanyDao {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumns = { 
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TYPE,		
			MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_BESCHREIBUNG,	
			MySQLiteHelper.COLUMN_PERSON,
			MySQLiteHelper.COLUMN_ADRESSE,
			MySQLiteHelper.COLUMN_TELEFON,
			MySQLiteHelper.COLUMN_INTERNET,
			MySQLiteHelper.COLUMN_EMAIL,
			MySQLiteHelper.COLUMN_BRANCHE,
			MySQLiteHelper.COLUMN_STANDORTE,
			MySQLiteHelper.COLUMN_MITARBEITER,
			MySQLiteHelper.COLUMN_EINSTELLUNGEN,
			MySQLiteHelper.COLUMN_EINSATZBEREICHE,			
			MySQLiteHelper.COLUMN_STAND_NUMMER,
			MySQLiteHelper.COLUMN_LOGO_NAME,
			MySQLiteHelper.COLUMN_PHOTO_NAME,
			MySQLiteHelper.COLUMN_IS_FAVORITE
			};
	public MySQLiteHelper getDbHelper() {
		return dbHelper;
	}

	public CompanyDao(Context context) {
		dbHelper = new MySQLiteHelper(context);		
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		//Log.d(TAG, dbHelper.getDatabaseName());		
	}

	public void close() {
		dbHelper.close();
	}
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumns, "type like \"A\"", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Company comp = cursorToCompany(cursor);
			companies.add(comp);
			cursor.moveToNext();
		}
		cursor.close();
		return companies;
	}

	private Company cursorToCompany(Cursor cursor) {
		for(int i = 1 ; i < cursor.getColumnCount() ; i++){
			Log.d("TAG", " column: "+ i + " " + cursor.getColumnName(i) + " Value: " + cursor.getString(i) );
		}
		
		Company comp = new Company();
		
		
		comp.setId(cursor.getInt(0));
		comp.setType(cursor.getString(1));
		comp.setName(cursor.getString(2));
		comp.setBeschreibung(cursor.getString(3));
		comp.setPerson(cursor.getString(4));
		comp.setAdresse(cursor.getString(5));
		comp.setTelefon(cursor.getString(6));
		comp.setInternet(cursor.getString(7));
		comp.setEmail(cursor.getString(8));
		comp.setBranche(cursor.getString(9));
		comp.setStandorte(cursor.getString(10));
		comp.setMitarbeiter(cursor.getString(11));
		comp.setEinstellungen(cursor.getString(12));
		comp.setEinsatzbereiche(cursor.getString(13));
		comp.setStand_nummer(cursor.getString(14));		
		comp.setLogo_name(cursor.getString(15));
		comp.setPhoto_name(cursor.getString(16));
		comp.setIs_favorite(cursor.getInt(17));
		return comp;
	}

	public Company findCompanyByID(long id) {
		Company result = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME_AUSSTELLER, allColumns, "type like \"A\" AND _id like "+ id, null, null, null, null);
		cursor.moveToFirst();
		result = cursorToCompany(cursor);
		cursor.close();
		return result;
	}
	

}
