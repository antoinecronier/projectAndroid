package com.antoineanais.windowsgesture;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Client {

	/* Members */
	private int id_client;
	private String nom;

	/* constructor */
	public Client(String nom) {
		super();
		this.nom = nom;
	}

	public Client(Context context, int id){
		super();
		this.id_client = id;
		getClientByID(context);
	}
	
	public Client(){
		super();
		this.id_client = 0;
		this.nom = "";
	}
	
	/* Getters & setters */
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/* Méthodes */
	
	/**
	 * Insert un client en base et set son id
	 * @param context
	 */
	public void insertClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.CLIENT_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.CLIENT_NOM, getNom());

		setId_client((int)db.insert(Constantes.TABLE_NAME_CLIENT, null, content));
		
		db.close();
	}

	/**
	 * Récupère le nom d'un client depuis son ID
	 * @param context
	 * @param ID
	 */
	public void getClientByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String WHERE = Constantes.CLIENT_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_client()) };

		monCu = db.query(Constantes.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setNom(monCu.getString(1));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère l'ID d'un client en fonction de son nom
	 * @param context
	 */
	public void getClientByNom(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String WHERE = Constantes.CLIENT_NOM + " = ?";
		String[] CLAUSE = { getNom() };

		monCu = db.query(Constantes.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setId_client(monCu.getInt(0));
			} while (monCu.moveToNext());
		}
		db.close();
	}
	
	/**
	 * Mise a jour des données du client
	 * @param context
	 * @return
	 */
	public int upDateClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.CLIENT_ID, getId_client());
		content.put(Constantes.CLIENT_NOM, getNom());

		toReturn += db.update(Constantes.TABLE_NAME_CLIENT, content,
				Constantes.CLIENT_ID+ " = ?", 
				new String[] { String.valueOf(getId_client()) });

		db.close();

		return toReturn;
	}

	/**
	 * Suppression du client
	 * @param context
	 */
	public void deletedClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_CLIENT, Constantes.CLIENT_ID + " = ?",
				new String[] { String.valueOf( getId_client()) });

		db.close();
	}
}
