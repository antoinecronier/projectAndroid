package com.antoineanais.windowsgesture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Produit {

	/* Members */
	private int id_produit;
	
	private enum Type{
		fenetre,
		porte
	};
	private enum Etat{
		encours,
		valide,
		rebut,
		standby
	};
	private enum Materiel{
		aluminium,
		acier,
		acierrenforce
	};
	private String type;
	private String etat;
	private String materiel;
	
	private int avancement;
	
	/* Getters & Setters */
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getAvancement() {
		return avancement;
	}
	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	
	/* Construtors */
	public Produit() {
		super();
		this.avancement = 0;
	}
	public Produit(int id_produit, Type type, Etat etat, Materiel materiel,
			int avancement) {
		super();
		this.id_produit = id_produit;
		this.type = type.toString();
		this.etat = etat.toString();
		this.materiel = materiel.toString();
		this.avancement = avancement;
	}
	
	/**
	 * Insert un produit en base et set son id
	 * @param context
	 */
	public void insertProduit(Context context) {
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
