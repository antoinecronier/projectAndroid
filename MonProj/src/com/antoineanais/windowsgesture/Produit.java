package com.antoineanais.windowsgesture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Produit {

	/* Members */
	private int id_produit;

	private enum Type {
		fenetre, porte
	};

	private enum Etat {
		encours, valide, rebut, standby
	};

	private enum Materiel {
		aluminium, acier, acierrenforce
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

	public Produit(Context context, int id_produit) {
		super();
		this.id_produit = id_produit;
		getProduitByID(context);
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

	/* Méthodes */
	
	/**
	 * Insert un produit en base et set son id
	 * 
	 * @param context
	 */
	public void insertProduit(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.PRODUIT_TYPE, Constantes.PRODUIT_ETAT,
				Constantes.PRODUIT_MATERIEL, Constantes.PRODUIT_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.PRODUIT_TYPE, getType());
		content.put(Constantes.PRODUIT_ETAT, getEtat());
		content.put(Constantes.PRODUIT_MATERIEL, getMateriel());
		content.put(Constantes.PRODUIT_AVANCEMENT, getAvancement());

		setId_produit((int) db.insert(Constantes.TABLE_NAME_PRODUIT, null,
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
	public int upDateProduit(Context context) {
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
	public void deletedProduit(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_PRODUIT,
				Constantes.PRODUIT_ID + " = ?",
				new String[] { String.valueOf(getId_produit()) });

		db.close();
	}
}
