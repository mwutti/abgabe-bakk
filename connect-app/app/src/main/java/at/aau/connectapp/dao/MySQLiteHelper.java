package at.aau.connectapp.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	//The Android's default system path of your application database.
	
	@SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/at.aau.connectapp/databases/";
	private static String DB_NAME = "connect2014.db";	
	public static String TABLE_NAME_AUSSTELLER = "Aussteller";
	public static String TABLE_NAME_STAND = "Stand";
	public static String TABLE_NAME_IMPULSVORTRAEGE = "Impulsvortraege";
	public static String TABLE_NAME_SPECIALS = "Specials";
	//Austeller
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_BESCHREIBUNG = "beschreibung";		
	public static final String COLUMN_ADRESSE = "adresse";
	public static final String COLUMN_TELEFON = "telefon";
	public static final String COLUMN_INTERNET = "internet";
	public static final String COLUMN_STAND_NUMMER = "stand_nummer";
	public static final String COLUMN_LOGO_NAME = "logo_name";	
	public static final String COLUMN_IS_FAVORITE = "is_favorite";
	//Company
	public static final String COLUMN_PERSON = "person";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_BRANCHE = "branche";
	public static final String COLUMN_STANDORTE = "standorte";
	public static final String COLUMN_MITARBEITER = "mitarbeiter";
	public static final String COLUMN_EINSTELLUNGEN = "einstellungen";
	public static final String COLUMN_EINSATZBEREICHE = "einsatzbereiche";
	public static final String COLUMN_PHOTO_NAME = "photo_name";
	//Educational
	public static final String COLUMN_STUDIENGAENGE = "studiengaenge";
	public static final String COLUMN_ZULASSUNGSVORRAUSSETZUNGEN = "zulassungsvorraussetzungen";
	public static final String COLUMN_ABSCHLUSS = "abschluss";
	//Stand
	public static final String COLUMN_NR = "stand_nummer";
	public static final String COLUMN_X1 = "x1";
	public static final String COLUMN_X2 = "x2";
	public static final String COLUMN_Y1 = "y1";
	public static final String COLUMN_Y2 = "y2";
	//Impulsvortraege
	public static final String COLUMN_VON = "von";
	public static final String COLUMN_BIS = "bis";
	
	


	private SQLiteDatabase myDataBase; 

	private final Context myContext;

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public MySQLiteHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}	

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist){
			//do nothing - database already exist
		}else{

			//By calling this method an empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){

		SQLiteDatabase checkDB = null;

		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);			

		}catch(SQLiteException e){
			//database does't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copies the database from the local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transferring bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
	
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{

		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if(myDataBase != null)
			myDataBase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}	
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_AUSSTELLER);
    onCreate(db);
  }

}