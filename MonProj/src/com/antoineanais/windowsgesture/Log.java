package com.antoineanais.windowsgesture;

import java.util.ArrayList;

import javax.crypto.Mac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Un log est lié a un produit et a une machine, il permet de connètre les
 * durées de fabrication ainsi que les dates d'usinage
 * 
 * @author alexandre
 * 
 */
public class Log {
	/* Members */
	private int id_log;
	private Produit produit;
	private Machine machine;
	private User user;
	private String duree;
	private String dateEntre;
	private String dateSortie;

	/* Getters & Setters */
	public int getId_log() {
		return id_log;
	}

	public void setId_log(int id_log) {
		this.id_log = id_log;
	}

	public Produit get_produit() {
		return produit;
	}

	public void set_produit(Produit produit) {
		this.produit = produit;
	}

	public Machine get_machine() {
		return machine;
	}

	public void set_machine(Machine machine) {
		this.machine = machine;
	}

	public String getDuree() {
		return duree;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getDateEntre() {
		return dateEntre;
	}

	public void setDateEntre(String dateEntre) {
		this.dateEntre = dateEntre;
	}

	public String getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	/* construtors */
	public Log(String duree, String dateEntre) {
		super();
		this.duree = duree;
		this.dateEntre = dateEntre;
	}

	public Log() {
		super();
	}

	/**
	 * Insert un log
	 * 
	 * @param context
	 */
	public void insertLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.LOG_PRODUIT_ID, Constantes.LOG_MACHINE_ID,
				Constantes.LOG_DUREE, Constantes.LOG_DATEENTREE,
				Constantes.LOG_DATESORTIE, Constantes.LOG_USER_ID };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.LOG_PRODUIT_ID, get_produit().getId_produit());
		content.put(Constantes.LOG_MACHINE_ID, get_machine().getId_machine());
		content.put(Constantes.LOG_DUREE, getDuree());
		content.put(Constantes.LOG_DATEENTREE, getDateEntre());
		content.put(Constantes.LOG_DATESORTIE, getDateSortie());
		content.put(Constantes.LOG_USER_ID, getUser().getId_user());

		setId_log((int) db.insert(Constantes.TABLE_NAME_LOG, null, content));

		db.close();
	}

	/**
	 * Récupère un log par le log_ID
	 * 
	 * @param context
	 */
	public void getLogByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE,
				Constantes.LOG_USER_ID };
		String WHERE = Constantes.LOG_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_log()) };

		monCu = db.query(Constantes.TABLE_NAME_LOG, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				set_produit(new Produit(context, monCu.getInt(1), db));
				set_machine(new Machine(context, monCu.getInt(2), db));
				setDuree(monCu.getString(3));
				setDateEntre(monCu.getString(4));
				setDateSortie(monCu.getString(5));
				setUser(new User(context, monCu.getInt(6), db));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Met a jour le log en base de donnée
	 * 
	 * @param context
	 * @return
	 */
	public int upDateLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE,
				Constantes.LOG_USER_ID };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.LOG_ID, getId_log());
		content.put(Constantes.LOG_PRODUIT_ID, get_produit().getId_produit());
		content.put(Constantes.LOG_MACHINE_ID, get_machine().getId_machine());
		content.put(Constantes.LOG_DUREE, getDuree());
		content.put(Constantes.LOG_DATEENTREE, getDateEntre());
		content.put(Constantes.LOG_DATESORTIE, getDateSortie());
		content.put(Constantes.LOG_USER_ID, getUser().getId_user());

		toReturn += db.update(Constantes.TABLE_NAME_LOG, content,
				Constantes.LOG_ID + " = ?",
				new String[] { String.valueOf(getId_log()) });

		db.close();

		return toReturn;
	}

	/**
	 * Récupère l'ensemble des logs pour un produit
	 * 
	 * @param context
	 * @param ID
	 * @return
	 */
	public ArrayList<Log> getLogstByProduitID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE,
				Constantes.LOG_USER_ID };
		String WHERE = Constantes.LOG_PRODUIT_ID + " = ?";
		String[] CLAUSE = { String.valueOf(ID) };

		monCu = db.query(Constantes.TABLE_NAME_LOG, COL, WHERE, CLAUSE, null,
				null, Constantes.LOG_DATEENTREE);

		ArrayList<Log> ListLogs = new ArrayList<Log>();

		if (monCu.moveToFirst()) {
			do {
				Log unListLog = new Log();
				unListLog
						.set_produit(new Produit(context, monCu.getInt(1), db));
				unListLog
						.set_machine(new Machine(context, monCu.getInt(2), db));
				unListLog.setDuree(monCu.getString(3));
				unListLog.setDateEntre(monCu.getString(4));
				unListLog.setDateSortie(monCu.getString(5));
				unListLog.setUser(new User(context, monCu.getInt(6), db));
				ListLogs.add(unListLog);
			} while (monCu.moveToNext());
		}
		db.close();
		return ListLogs;
	}

	/**
	 * Récupère l'ensemble des logs pour une machine
	 * 
	 * @param context
	 * @param ID
	 * @return
	 */
	public ArrayList<Log> getLogstByMachineID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE,
				Constantes.LOG_USER_ID };
		String WHERE = Constantes.LOG_MACHINE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(ID) };

		monCu = db.query(Constantes.TABLE_NAME_LOG, COL, WHERE, CLAUSE, null,
				null, Constantes.LOG_DATEENTREE);

		ArrayList<Log> ListLogs = new ArrayList<Log>();

		if (monCu.moveToFirst()) {
			do {
				Log unListLog = new Log();
				unListLog
						.set_produit(new Produit(context, monCu.getInt(1), db));
				unListLog
						.set_machine(new Machine(context, monCu.getInt(2), db));
				unListLog.setDuree(monCu.getString(3));
				unListLog.setDateEntre(monCu.getString(4));
				unListLog.setDateSortie(monCu.getString(5));
				unListLog.setUser(new User(context, monCu.getInt(6), db));
				ListLogs.add(unListLog);
			} while (monCu.moveToNext());
		}
		db.close();
		return ListLogs;
	}

	/**
	 * Récupère l'ensemble des logs pour un user id
	 * 
	 * @param context
	 * @param ID
	 * @return
	 */
	public ArrayList<Log> getLogstByUserID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE,
				Constantes.LOG_USER_ID };
		String WHERE = Constantes.LOG_MACHINE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(ID) };

		monCu = db.query(Constantes.TABLE_NAME_LOG, COL, WHERE, CLAUSE, null,
				null, Constantes.LOG_DATEENTREE);

		ArrayList<Log> ListLogs = new ArrayList<Log>();

		if (monCu.moveToFirst()) {
			do {
				Log unListLog = new Log();
				unListLog
						.set_produit(new Produit(context, monCu.getInt(1), db));
				unListLog
						.set_machine(new Machine(context, monCu.getInt(2), db));
				unListLog.setDuree(monCu.getString(3));
				unListLog.setDateEntre(monCu.getString(4));
				unListLog.setDateSortie(monCu.getString(5));
				unListLog.setUser(new User(context, monCu.getInt(6), db));
				ListLogs.add(unListLog);
			} while (monCu.moveToNext());
		}
		db.close();
		return ListLogs;
	}

	/**
	 * Supprime le log de la base de donnée
	 * 
	 * @param context
	 */
	public void deletedLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_LOG, Constantes.LOG_ID + " = ?",
				new String[] { String.valueOf(getId_log()) });

		db.close();
	}
}
