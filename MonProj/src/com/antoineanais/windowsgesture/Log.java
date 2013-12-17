package com.antoineanais.windowsgesture;

import javax.crypto.Mac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Log {
	/* Members */
	private int id_log;
	private Produit produit;
	private Machine machine;
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

	/**
	 * Insert a log
	 * 
	 * @param context
	 */
	public void insertLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.LOG_PRODUIT_ID, Constantes.LOG_MACHINE_ID,
				Constantes.LOG_DUREE, Constantes.LOG_DATEENTREE,
				Constantes.LOG_DATESORTIE };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.LOG_PRODUIT_ID, get_produit().getId_produit());
		content.put(Constantes.LOG_MACHINE_ID, get_machine().getId_machine());
		content.put(Constantes.LOG_DUREE, getDuree());
		content.put(Constantes.LOG_DATEENTREE, getDateEntre());
		content.put(Constantes.LOG_DATESORTIE, getDateSortie());

		setId_log((int) db.insert(Constantes.TABLE_NAME_LOG, null, content));

		db.close();
	}

	/**
	 * Get a log using log_ID
	 * 
	 * @param context
	 * @param ID
	 */
	public void getLogByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE };
		String WHERE = Constantes.LOG_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_log()) };

		monCu = db.query(Constantes.TABLE_NAME_LOG, COL, WHERE, CLAUSE,
				null, null, null);
		if (monCu.moveToFirst()) {
			do {
				set_produit(new Produit(context, monCu.getInt(1)));
				set_machine(new Machine(context, monCu.getInt(2)));
				setDuree(monCu.getString(3));
				setDateEntre(monCu.getString(4));
				setDateSortie(monCu.getString(5));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	public int upDateLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.LOG_ID, Constantes.LOG_PRODUIT_ID,
				Constantes.LOG_MACHINE_ID, Constantes.LOG_DUREE,
				Constantes.LOG_DATEENTREE, Constantes.LOG_DATESORTIE };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.LOG_ID, getId_log());
		content.put(Constantes.LOG_PRODUIT_ID, get_produit().getId_produit());
		content.put(Constantes.LOG_MACHINE_ID, get_machine().getId_machine());
		content.put(Constantes.LOG_DUREE, getDuree());
		content.put(Constantes.LOG_DATEENTREE, getDateEntre());
		content.put(Constantes.LOG_DATESORTIE, getDateSortie());
		
		toReturn += db.update(Constantes.TABLE_NAME_LOG, content,
				Constantes.LOG_ID + " = ?",
				new String[] { String.valueOf(getId_log())});

		db.close();

		return toReturn;
	}

	public void deletedLog(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_LOG, Constantes.LOG_ID + " = ?",
				new String[] { String.valueOf(getId_log()) });

		db.close();
	}
}
