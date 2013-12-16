package com.antoineanais.windowsgesture;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLite extends SQLiteOpenHelper{

	// Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "WindowsGesture";
	
    //Tables Name
	private static final String TABLE_NAME_USER = "USER";
	private static final String TABLE_NAME_ZONE = "ZONE";
	private static final String TABLE_NAME_COMMANDE = "COMMANDE";
	private static final String TABLE_NAME_CLIENT = "CLIENT";
	private static final String TABLE_NAME_PRODUIT = "PRODUIT";
	private static final String TABLE_NAME_LOG = "LOG";
	private static final String TABLE_NAME_MACHINE = "MACHINE";
	
	//Columns name -USER-
	private static final String USER_ID = "id_user";
	private static final String USER_LOGIN = "login";
	private static final String USER_PWD = "password";
	private static final String USER_ROLE = "role";
	
	//Columns name -ZONE-
	private static final String ZONE_ID = "id_zone";
	private static final String ZONE_NOM = "nom";
	
	//Columns name -MACHINE-
	private static final String MACHINE_ID = "id_user";
	private static final String MACHINE_ZONE_ID = "id_zone";
	
	//Columns name -COMMANDE-
	private static final String COMMANDE_ID = "id_cmd";
	private static final String COMMANDE_DATE_DEBUT = "dateCreation";
	private static final String COMMANDE_DATE_FIN = "dateFin";
	private static final String COMMANDE_DATE_LIVRAISON = "dateLivraison";
	private static final String COMMANDE_AVANCEMENT = "avancement";
	private static final String COMMANDE_PRODUITS = "produits";
	private static final String COMMANDE_CLIENT_ID = "id_client";
	
	//Columns name -CLIENT-
	private static final String CLIENT_ID = "id_client";
	private static final String CLIENT_NOM = "nom";
	
	//Columns name -PRODUIT-
	private static final String PRODUIT_ID = "id_produit";
	private static final String PRODUIT_AVANCEMENT = "avancement";
	private static final String PRODUIT_TYPE = "type";
	private static final String PRODUIT_ETAT = "etat";
	private static final String PRODUIT_MATERIEL = "materiel";
	
	//Columns name -LOG-
	private static final String LOG_ID = "id_log";
	private static final String LOG_PRODUIT_ID = "id_produit";
	private static final String LOG_MACHINE_ID = "id_machine";
	private static final String LOG_DUREE = "duree";
	private static final String LOG_DATE_ENTREE = "dateEntre";
	private static final String LOG_DATE_SORTIE = "dateSortie";
	
	//Create Table USER
	private static final String CREATE_TABLE_USER =
				"CREATE TABLE "+TABLE_NAME_USER+""+
				"("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				""+USER_LOGIN+" TEXT NOT NULL," +
				""+USER_PWD+" TEXT NOT NULL," +
				""+USER_ROLE+" INTEGER NOT NULL);";
	
	//Create Table Client
	private static final String CREATE_TABLE_CLIENT = 
			"CREATE TABLE "+TABLE_NAME_CLIENT+""+
			"("+CLIENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
			""+CLIENT_NOM+" TEXT NOT NULL);";
	
	//Create Table ZONE
	private static final String	CREATE_TABLE_ZONE = 
			"CREATE TABLE "+TABLE_NAME_ZONE+""+
			"("+ZONE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
			""+ZONE_NOM+" TEXT NOT NULL );";
	
	//Create Table PRODUIT
	private static final String	CREATE_TABLE_PRODUIT = 
			"CREATE TABLE "+TABLE_NAME_PRODUIT+""+
			"("+PRODUIT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
			""+PRODUIT_TYPE+" TEXT NOT NULL," +
			""+PRODUIT_ETAT+" TEXT NOT NULL," +
			""+PRODUIT_MATERIEL+" TEXT NOT NULL," +
			""+PRODUIT_AVANCEMENT+" INTEGER );";
	
	//CREATE Table MACHINE
	private static final String CREATE_TABLE_MACHINE = 
			"CREATE TABLE "+TABLE_NAME_MACHINE+""+
			"("+MACHINE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
			""+MACHINE_ZONE_ID+" INTEGER NOT NULL " +
			" FOREIGN KEY("+ZONE_ID+") " +
			"REFERENCES "+TABLE_NAME_ZONE+"("+MACHINE_ZONE_ID+"));";
	
	//CREATE Table COMMANDE
	private static final String CREATE_TABLE_COMMANDE = 
				"CREATE TABLE "+TABLE_NAME_COMMANDE+""+
				"("+COMMANDE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				""+COMMANDE_CLIENT_ID+" INTEGER NOT NULL " +
				""+COMMANDE_DATE_DEBUT+" TEXT NOT NULL," +
				""+COMMANDE_DATE_FIN+" TEXT," +
				""+COMMANDE_DATE_LIVRAISON+" TEXT," +
				""+COMMANDE_AVANCEMENT+" INTEGER" +
				" FOREIGN KEY("+CLIENT_ID+") " +
				"REFERENCES "+TABLE_NAME_CLIENT+"("+COMMANDE_CLIENT_ID+"));";
	
	//CREATE Table LOG
		private static final String CREATE_TABLE_LOG = 
				"CREATE TABLE "+TABLE_NAME_LOG+""+
				"("+LOG_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				""+LOG_PRODUIT_ID+" INTEGER NOT NULL " +
				""+LOG_MACHINE_ID+" INTEGER NOT NULL " +
				""+LOG_DUREE+" TEXT," +
				""+LOG_DATE_ENTREE+" TEXT," +
				""+LOG_DATE_SORTIE+" TEXT," +
				" FOREIGN KEY("+LOG_PRODUIT_ID+") " +
				"REFERENCES "+TABLE_NAME_PRODUIT+"("+PRODUIT_ID+")" +
				"FOREIGN KEY("+LOG_MACHINE_ID+") " +
				"REFERENCES "+TABLE_NAME_MACHINE+"("+MACHINE_ID+");";
	
	public DatabaseSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create BDD
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stubEnable foreign key constraints
	}
	
	public void onConfigure (SQLiteDatabase db){
		if (!db.isReadOnly()) {
		    // enable foreign key
		    db.execSQL("PRAGMA foreign_keys=ON;");
		  }
	}
}
