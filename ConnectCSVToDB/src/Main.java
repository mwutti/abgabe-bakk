import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{

	final static String DB_NAME = "connect2014";
	final static String TABLE_NAME_AUSSTELLER ="Aussteller";
	final static String TABLE_NAME_STAND = "Stand";
	final static String TABLE_NAME_SPECIALS = "Specials";
	final static String TABLE_NAME_IMPULSVORTRAEGE = "Impulsvortraege";

	final static String ID = "_id";
	final static String TYPE = "type";
	final static String NAME = "name";
	final static String BESCHREIBUNG = "beschreibung";
	final static String PERSON = "person";
	final static String ADRESSE = "adresse";
	final static String TELEFON = "telefon";
	final static String INTERNET ="internet";
	final static String EMAIL = "email";
	final static String BRANCHE ="branche";
	final static String STANDORTE ="standorte";
	final static String MITARBEITER ="mitarbeiter";
	final static String STUDIENGAENGE ="studiengaenge";
	final static String EINSTELLUNGEN ="einstellungen";
	final static String ZULASSUNGSVORRAUSSETZUNGEN ="zulassungsvorraussetzungen";
	final static String EINSATZBEREICH = "einsatzbereiche";
	final static String ABSCHLUSS ="abschluss";
	final static String STAND_NUMMER ="stand_nummer";
	final static String LOGO_NAME = "logo_name";
	final static String PHOTO_NAME = "photo_name";
	final static String IS_FAVORITE = "is_favorite";

	final static String STANDNUMMERNUMMER = "standnummernummer";
	final static String X1 = "x1";
	final static String X2 = "x2";
	final static String Y1 = "y1";
	final static String Y2 = "y2";

	final static String	VON = "von";
	final static String BIS = "bis";


	public static String getCreateStatement(){

		String create = "--Script for creating the connect Database\n\n";

		create += "DROP TABLE IF EXISTS android_metadata;\n";
		create += "DROP TABLE IF EXISTS " + TABLE_NAME_AUSSTELLER + ";\n";		
		create += "DROP TABLE IF EXISTS " + TABLE_NAME_IMPULSVORTRAEGE + ";\n";
		create += "DROP TABLE IF EXISTS " + TABLE_NAME_SPECIALS + ";\n";
		create += "DROP TABLE IF EXISTS " + TABLE_NAME_STAND + ";\n\n";
		//Android metadata table

		create += "CREATE TABLE android_metadata(locale TEXT DEFAULT 'de');\n";
		create += "INSERT INTO android_metadata VALUES ('de');\n\n";

		//Stand table
		create += "CREATE TABLE "+ TABLE_NAME_STAND +" (\n"
				+ ID + " INTEGER PRIMARY KEY,\n"
				+ STAND_NUMMER + " TEXT,\n"
				+ X1 + " INTEGER,\n"
				+ X2 + " INTEGER,\n"
				+ Y1 + " INTEGER,\n"
				+ Y2 + " INTEGER);\n\n";

		//Aussteller table
		create += "CREATE TABLE "+ TABLE_NAME_AUSSTELLER +"(\n" 
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT"+",\n"
				+ TYPE+ " INTEGER NOT NULL"+",\n"
				+ NAME + " TEXT NOT NULL" +",\n"
				+ BESCHREIBUNG + " TEXT NOT NULL"+",\n"
				+ PERSON + " TEXT "+",\n"
				+ ADRESSE + " TEXT"+",\n" 
				+ TELEFON + " TEXT"+",\n"
				+ INTERNET + " TEXT"+",\n"
				+ EMAIL + " TEXT"+",\n"
				+ BRANCHE + " TEXT" +",\n"
				+ STANDORTE + " TEXT"+",\n"
				+ MITARBEITER + " TEXT"+",\n"
				+ EINSTELLUNGEN + " TEXT"+",\n"
				+ STUDIENGAENGE + " TEXT"+",\n"				
				+ ZULASSUNGSVORRAUSSETZUNGEN + " TEXT"+",\n"
				+ EINSATZBEREICH + " TEXT"+",\n"
				+ ABSCHLUSS +" TEXT"+",\n"
				+ STAND_NUMMER + " TEXT "+",\n"		
				+ LOGO_NAME + " TEXT "+",\n"	
				+ PHOTO_NAME + " TEXT " + ",\n"
				+ IS_FAVORITE + " INTEGER " + ",\n"
				+ "FOREIGN KEY("+ STAND_NUMMER +") REFERENCES "+ TABLE_NAME_STAND+"("+ ID +")"+"\n);\n";

		//Special Table
		create += "CREATE TABLE " + TABLE_NAME_SPECIALS + "(\n"
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" 
				+ BESCHREIBUNG + " TEXT NOT NULL,\n"
				+ STAND_NUMMER + " TEXT NOT NULL,\n"
				+ "FOREIGN KEY("+ STAND_NUMMER + ") REFERENCES " + TABLE_NAME_STAND+"("+ ID + ")"+"\n);\n";

		//Impulsvorträge Table
		create += "CREATE TABLE " + TABLE_NAME_IMPULSVORTRAEGE + "(\n"
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" 
				+ NAME + " TEXT NOT NULL,\n"
				+ BESCHREIBUNG + " TEXT NOT NULL,\n"
				+ VON + " TEXT NOT NULL,\n"
				+ BIS + " TEXT NOT NULL,\n"
				+ STAND_NUMMER + " TEXT NOT NULL,\n"
				+ "FOREIGN KEY("+ STAND_NUMMER + ") REFERENCES " + TABLE_NAME_STAND+"("+ ID + ")"+"\n);\n";


		return create;
	}

	public static String getInsertAussteller(String columns[]){						

		String insert = "INSERT INTO "+ TABLE_NAME_AUSSTELLER + " (" +TYPE + "," + NAME + "," + BESCHREIBUNG + "," + PERSON + "," + ADRESSE + "," + TELEFON + "," + INTERNET + "," + EMAIL + "," + BRANCHE
				+ "," + STANDORTE + "," + MITARBEITER + "," + EINSTELLUNGEN + "," + STUDIENGAENGE + "," + ZULASSUNGSVORRAUSSETZUNGEN + "," + EINSATZBEREICH + ","+ ABSCHLUSS + "," + STAND_NUMMER +","+LOGO_NAME + ","+ PHOTO_NAME + "," + IS_FAVORITE + ") \nVALUES(";
		for(int i = 0 ; i < columns.length-1 ; i++){
			insert += "\""+columns[i] +"\""+ ",";
		}		
		insert += "\""+columns[columns.length-1]+"\",";
		insert += "0" + ");\n";

		return insert;
	}

	public static String getInsertStaende(String columns[]){

		String insert = "INSERT INTO " + TABLE_NAME_STAND + " (" + STAND_NUMMER  + "," + X1 + "," + X2 + "," + Y1 + "," + Y2 +") \nVALUES( ";
		for (int i = 0 ; i < columns.length-1 ; i++){
			insert += "\""+ columns[i] +"\""+ ",";
		}
		insert += "\""+ columns[columns.length - 1] + "\");\n";

		return insert;
	}
	
	public static String getInsertSpecial(String columns[]){
		String insert = "INSERT INTO " + TABLE_NAME_SPECIALS + " (" + BESCHREIBUNG  + "," + STAND_NUMMER + ") \nVALUES( ";
		for (int i = 0 ; i < columns.length-1 ; i++){
			insert += "\""+ columns[i] +"\""+ ",";
		}
		insert += "\""+ columns[columns.length - 1] + "\");\n";

		return insert;
	}
	
	public static String getInsertImpulsvortraege(String columns[]){
		String insert = "INSERT INTO " + TABLE_NAME_IMPULSVORTRAEGE + " (" + NAME + "," + BESCHREIBUNG  + "," + VON + ","+ BIS + "," + STAND_NUMMER + ") \nVALUES( ";
		for (int i = 0 ; i < columns.length-1 ; i++){
			insert += "\""+ columns[i] +"\""+ ",";
		}
		insert += "\""+ columns[columns.length - 1] + "\");\n";

		return insert;
	}
	public static void main(String args[]){

		FileReader inputFileStaende = null;
		FileReader inputFileAussteller = null;
		FileReader inputFileSpecials = null;
		FileReader inputFileImpulsvortraege = null;
		
		PrintWriter out = null;
		String line = null;
		
		String columnsAussteller[]= new String[19];	
		String columnsStaende[] = new String[5];
		String columnsSpecials[] = new String[2];
		String columnsImpulsvortraege[] = new String[5];
		
		String insert= null;

		try {
			out = new PrintWriter("scripts.db","windows-1252");
			out.println(Main.getCreateStatement());		
			inputFileStaende = new FileReader("staende.csv");
			inputFileAussteller = new FileReader("aussteller.csv");
			inputFileImpulsvortraege = new FileReader("impulsvortraege.csv");
			inputFileSpecials = new FileReader("specials.csv");

			BufferedReader in = new BufferedReader(inputFileStaende);

			while((line = in.readLine()) != null){
				columnsStaende = line.split(",");
				insert = Main.getInsertStaende(columnsStaende);
				out.append(insert);
				System.out.println("#Columns: "+ columnsStaende.length);
				System.out.println(insert);
			}
			in = new BufferedReader(inputFileAussteller);

			while((line = in.readLine()) != null){

				columnsAussteller = line.split(";");
				insert = Main.getInsertAussteller(columnsAussteller);
				out.append(insert);
				System.out.println("#Columns: "+columnsAussteller.length);
				System.out.println(insert);										

			}
			in = new BufferedReader(inputFileSpecials);
			while((line = in.readLine()) != null){
				columnsSpecials = line.split(";");
				insert = Main.getInsertSpecial(columnsSpecials);
				out.append(insert);
				System.out.println("#Columns: "+ columnsSpecials.length);
				System.out.println(insert);
			}
			
			in = new BufferedReader(inputFileImpulsvortraege);
			while((line = in.readLine()) != null){
				columnsImpulsvortraege = line.split(";");
				insert = Main.getInsertImpulsvortraege(columnsImpulsvortraege);
				out.append(insert);
				System.out.println("#Columns: "+ columnsImpulsvortraege.length);
				System.out.println(insert);
			}
			
			out.close();

		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}	



	}
}

