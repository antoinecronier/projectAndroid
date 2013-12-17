package com.antoineanais.windowsgesture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Zone {

	/*Members*/
	private int id_zone;
	private  String nom;
	
	/*Getters & setters*/
	public int getId_zone() {
		return id_zone;
	}
	public void setId_zone(int id_zone) {
		this.id_zone = id_zone;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/*constructor*/
	public Zone(int id_zone) {
		super();
		this.id_zone = id_zone;
	}
	
	/* Méthodes */

	/**
	 * Insert la zone en base et set son id
	 * 
	 * @param context
	 */
	public void insertZone(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.ZONE_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.ZONE_NOM, getNom());

		setId_zone((int) db.insert(Constantes.TABLE_NAME_ZONE, null, content));

		db.close();
	}

	/**
	 * Récupère la zone depuis son ID
	 * 
	 * @param context
	 * @param ID
	 */
	public void getUserByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.ZONE_ID, Constantes.ZONE_NOM };
		String WHERE = Constantes.ZONE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_zone()) };

		monCu = db.query(Constantes.TABLE_NAME_ZONE, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setNom(monCu.getString(1));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Mise a jour des données de la zone
	 * 
	 * @param context
	 * @return
	 */
	public int upDateUser(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.ZONE_ID, Constantes.ZONE_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.ZONE_ID, getId_zone());
		content.put(Constantes.ZONE_NOM, getNom());

		toReturn += db.update(Constantes.TABLE_NAME_ZONE, content,
				Constantes.ZONE_ID + " = ?",
				new String[] { String.valueOf(getId_zone()) });

		db.close();

		return toReturn;
	}

	/**
	 * Suppression de la zone
	 * 
	 * @param context
	 */
	public void deletedUser(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_ZONE, Constantes.ZONE_ID + " = ?",
				new String[] { String.valueOf(getId_zone()) });

		db.close();
	}
}
