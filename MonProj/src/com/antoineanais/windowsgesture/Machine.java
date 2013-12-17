package com.antoineanais.windowsgesture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Machine {

	/*Members*/
	private int id_machine;
	private Zone zone;

	/*Getters & setters*/
	public int getId_machine() {
		return id_machine;
	}
	public void setId_machine(int id_machine) {
		this.id_machine = id_machine;
	}
	public Zone get_zone() {
		return zone;
	}
	public void set_zone(Zone zone) {
		this.zone = zone;
	}	
	
	/*constructor*/
	public Machine() {
		super();
	}
	
	/* Méthodes */
	
	/**
	 * Insert une machine en base et set son id
	 * 
	 * @param context
	 */
	public void insertMachine(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.ma, Constantes.PRODUIT_ETAT,
				Constantes.PRODUIT_MATERIEL, Constantes.PRODUIT_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.PRODUIT_TYPE, getType());
		content.put(Constantes.PRODUIT_ETAT, getEtat());
		content.put(Constantes.PRODUIT_MATERIEL, getMateriel());
		content.put(Constantes.PRODUIT_AVANCEMENT, getAvancement());

		setId_produit((int) db.insert(Constantes.TABLE_NAME_MACHINE, null,
				content));

		db.close();
	}

	/**
	 * Récupère le produit depuis son ID
	 * 
	 * @param context
	 * @param ID
	 */
	public void getProduitByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.PRODUIT_ID, Constantes.PRODUIT_TYPE,
				Constantes.PRODUIT_ETAT, Constantes.PRODUIT_MATERIEL,
				Constantes.PRODUIT_AVANCEMENT };
		String WHERE = Constantes.PRODUIT_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_produit()) };

		monCu = db.query(Constantes.TABLE_NAME_PRODUIT, COL, WHERE, CLAUSE,
				null, null, null);
		if (monCu.moveToFirst()) {
			do {
				setType(monCu.getString(1));
				setEtat(monCu.getString(2));
				setMateriel(monCu.getString(3));
				setAvancement(monCu.getInt(4));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Mise a jour des données du produit
	 * 
	 * @param context
	 * @return
	 */
	public int upDateClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.PRODUIT_ID, Constantes.PRODUIT_TYPE,
				Constantes.PRODUIT_ETAT, Constantes.PRODUIT_MATERIEL,
				Constantes.PRODUIT_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.PRODUIT_ID, getId_produit());
		content.put(Constantes.PRODUIT_TYPE, getType());
		content.put(Constantes.PRODUIT_ETAT, getEtat());
		content.put(Constantes.PRODUIT_MATERIEL, getMateriel());
		content.put(Constantes.PRODUIT_AVANCEMENT, getAvancement());

		toReturn += db.update(Constantes.TABLE_NAME_PRODUIT, content,
				Constantes.PRODUIT_ID + " = ?",
				new String[] { String.valueOf(getId_produit()) });

		db.close();

		return toReturn;
	}

	/**
	 * Suppression du produit
	 * 
	 * @param context
	 */
	public void deletedClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_PRODUIT,
				Constantes.PRODUIT_ID + " = ?",
				new String[] { String.valueOf(getId_produit()) });

		db.close();
	}
}
