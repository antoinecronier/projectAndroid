package com.antoineanais.windowsgesture;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe de liaison avec la BDD
 * 
 * @author Nanis
 * 
 */
public class DatabaseSQLite extends SQLiteOpenHelper {

	public DatabaseSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create BDD
		db.execSQL(Constantes.CREATE_TABLE_USER);
		db.execSQL(Constantes.CREATE_TABLE_CLIENT);
		db.execSQL(Constantes.CREATE_TABLE_ZONE);
		db.execSQL(Constantes.CREATE_TABLE_PRODUIT);
		db.execSQL(Constantes.CREATE_TABLE_MACHINE);
		db.execSQL(Constantes.CREATE_TABLE_COMMANDE);
		db.execSQL(Constantes.CREATE_TABLE_LOG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stubEnable foreign key constraints
	}

	/**
	 * Configuration de la bdd pour utilisation des foreign key
	 */
	public void onConfigure(SQLiteDatabase db) {
		if (!db.isReadOnly()) {
			// enable foreign key
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	/**
	 * Récupère et renvoi l'ensemble des clients
	 * 
	 * @return ArrayList<Client>
	 */
	public ArrayList<Client> getAllUsers() {
		ArrayList<Client> users = new ArrayList<Client>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_CLIENT;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Client client = new Client();
				client.setId_client(Integer.parseInt(cursor.getString(0)));
				client.setNom(cursor.getString(1));
				// ajout du client dans la liste
				users.add(client);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return users;
	}

	/**
	 * Récupère et renvoi la liste de l'ensemble des commandes
	 * @param context
	 * @return ArrayList<Commande>
	 */
	public ArrayList<Commande> getAllCommandes(Context context) {
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_COMMANDE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Commande commande = new Commande();
				commande.setId_cmd(Integer.parseInt(cursor.getString(0)));
				commande.setClient(new Client(context,
						Integer.parseInt(cursor.getString(1))));
				commande.setDateCreation(cursor.getString(2));
				commande.setDateFin(cursor.getString(3));
				commande.setDateLivraison(cursor.getString(4));
				commande.setAvancement(Integer.parseInt(cursor.getString(5)));
				// ajout de la commande dans la liste
				commandes.add(commande);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return commandes;
	}
	
	/**
	 * Récupère et renvoi la liste de l'ensemble des logs
	 * @param context
	 * @return ArrayList<Logs>
	 */
	public ArrayList<Log> getAllLogs(Context context) {
		ArrayList<Log> logs = new ArrayList<Log>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_LOG;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Log log = new Log();
				log.setId_log(Integer.parseInt(cursor.getString(0)));
				log.set_produit(new Produit(context,
						Integer.parseInt(cursor.getString(1))));
				log.set_machine(new Machine(context, 
						Integer.parseInt(cursor.getString(2))));
				log.setUser(new User(context,
						Integer.parseInt(cursor.getString(3)),db));
				log.setDuree(cursor.getString(4));
				log.setDateEntre(cursor.getString(5));
				log.setDateSortie(cursor.getString(6));
				// ajout du log dans la liste
				logs.add(log);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return logs;
	}
	
	/**
	 * Récupère et renvoi la liste de l'ensemble des machines
	 * @param context
	 * @return ArrayLsit<Machine>
	 */
	public ArrayList<Machine> getAllMachines(Context context) {
		ArrayList<Machine> machines = new ArrayList<Machine>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_MACHINE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Machine machine = new Machine();
				machine.setId_machine(Integer.parseInt(cursor.getString(0)));
				machine.setNom(cursor.getString(1));
				machine.setZone(new Zone(context,
						Integer.parseInt(cursor.getString(2))));
				// ajout de la machine dans la liste
				machines.add(machine);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return machines;
	}
	
	/**
	 * Récupère et renvoi la liste de l'ensemble des produits
	 * @param context
	 * @return ArrayList<Produit>
	 */
	public ArrayList<Produit> getAllProduits(Context context) {
		ArrayList<Produit> produits = new ArrayList<Produit>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_PRODUIT;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Produit produit = new Produit();
				produit.setId_produit(Integer.parseInt(cursor.getString(0)));
				produit.setType(cursor.getString(1));
				produit.setEtat(cursor.getString(2));
				produit.setMateriel(cursor.getString(3));
				produit.setId_commande(Integer.parseInt(cursor.getString(4)));
				produit.setAvancement(Integer.parseInt(cursor.getString(5)));
				// ajout du produit dans la liste
				produits.add(produit);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return produits;
	}
	
	/**
	 * Récupère et renvoi la liste de l'ensemble des users
	 * @param context
	 * @returnArrayList<User>
	 */
	public ArrayList<User> getAllUsers(Context context) {
		ArrayList<User> users = new ArrayList<User>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_USER;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				User user = new User(context, 
						Integer.parseInt(cursor.getString(0)), db);
				user.setLogin(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				user.setRole(Integer.parseInt(cursor.getString(3)));
				// ajout de l'user dans la liste
				users.add(user);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return users;
	}
	
	/**
	 * Récupère et renvoi la liste de l'ensemble des zones
	 * @param context
	 * @return ArrayList<Zone>
	 */
	public ArrayList<Zone> getAllZones(Context context) {
		ArrayList<Zone> zones = new ArrayList<Zone>();
		// requête de sélection
		String selectQuery = "SELECT  * FROM " + Constantes.TABLE_NAME_ZONE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Récupération des données et ajout dans l'objet
		if (cursor.moveToFirst()) {
			do {
				Zone zone = new Zone(context, 
						Integer.parseInt(cursor.getString(0)));
				zone.setNom(cursor.getString(1));
				// ajout de la zone dans la liste
				zones.add(zone);
			} while (cursor.moveToNext());
		}

		// retour de la liste
		return zones;
	}
}
