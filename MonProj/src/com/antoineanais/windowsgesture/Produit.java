package com.antoineanais.windowsgesture;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Définit un produit, un produit possède une quantité, ainsi qu'un état, un
 * matériel et un type
 * 
 * @author alexandre
 * 
 */
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
	private int id_commande;

	private int quantite;
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

	public int getQuantite() {
		return quantite;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
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
		this.etat = Etat.standby.toString();
		this.avancement = 0;
	}

	public Produit(Context context, int id_produit) {
		super();
		this.id_produit = id_produit;
		getProduitByID(context);
	}

	public Produit(Context context, int id_produit, SQLiteDatabase db) {
		super();
		this.id_produit = id_produit;
		getProduitByIDForLogs(context, db);
	}

	public Produit(Context context, int id_commande, Object a) {
		super();
		this.id_commande = id_commande;
		getProduitByCommandeID(context);
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
				Constantes.PRODUIT_MATERIEL, Constantes.PRODUIT_AVANCEMENT,
				Constantes.PRODUIT_COMMANDE_ID };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.PRODUIT_TYPE, getType());
		content.put(Constantes.PRODUIT_ETAT, getEtat());
		content.put(Constantes.PRODUIT_MATERIEL, getMateriel());
		content.put(Constantes.PRODUIT_AVANCEMENT, getAvancement());
		content.put(Constantes.PRODUIT_COMMANDE_ID, getId_commande());

		setId_produit((int) db.insert(Constantes.TABLE_NAME_PRODUIT, null,
				content));

		db.close();
	}

	/**
	 * Récupère le produit depuis son ID
	 * 
	 * @param context
	 */
	public void getProduitByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.PRODUIT_ID, Constantes.PRODUIT_TYPE,
				Constantes.PRODUIT_ETAT, Constantes.PRODUIT_MATERIEL,
				Constantes.PRODUIT_AVANCEMENT, Constantes.PRODUIT_COMMANDE_ID };
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
				setId_commande(monCu.getInt(5));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère le produit depuis son ID pour les listes de logs
	 * 
	 * @param context
	 * @param db
	 */
	public void getProduitByIDForLogs(Context context, SQLiteDatabase db) {
		Cursor monCu;

		String[] COL = { Constantes.PRODUIT_ID, Constantes.PRODUIT_TYPE,
				Constantes.PRODUIT_ETAT, Constantes.PRODUIT_MATERIEL,
				Constantes.PRODUIT_AVANCEMENT, Constantes.PRODUIT_COMMANDE_ID };
		String WHERE = Constantes.PRODUIT_COMMANDE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_commande()) };

		monCu = db.query(Constantes.TABLE_NAME_PRODUIT, COL, WHERE, CLAUSE,
				null, null, null);
		if (monCu.moveToFirst()) {
			do {
				setId_produit(monCu.getInt(0));
				setType(monCu.getString(1));
				setEtat(monCu.getString(2));
				setMateriel(monCu.getString(3));
				setAvancement(monCu.getInt(4));
			} while (monCu.moveToNext());
		}
	}

	/**
	 * Récupère les produits depuis l'ID de la commande
	 * 
	 * @param context
	 * @return
	 */
	public ArrayList<Produit> getProduitByCommandeID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();
		Cursor monCu;

		String[] COL = { Constantes.PRODUIT_ID, Constantes.PRODUIT_TYPE,
				Constantes.PRODUIT_ETAT, Constantes.PRODUIT_MATERIEL,
				Constantes.PRODUIT_AVANCEMENT, Constantes.PRODUIT_COMMANDE_ID };
		String WHERE = Constantes.PRODUIT_COMMANDE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_commande()) };

		monCu = db.query(Constantes.TABLE_NAME_PRODUIT, COL, WHERE, CLAUSE,
				null, null, null);
		ArrayList<Produit> produits = new ArrayList<Produit>();
		if (monCu.moveToFirst()) {
			do {
				Produit monProduit = new Produit();
				monProduit.setId_produit(monCu.getInt(0));
				monProduit.setType(monCu.getString(1));
				monProduit.setEtat(monCu.getString(2));
				monProduit.setMateriel(monCu.getString(3));
				monProduit.setAvancement(monCu.getInt(4));
				monProduit.setId_commande(getId_commande());
				produits.add(monProduit);
			} while (monCu.moveToNext());
		}
		return produits;
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
				Constantes.PRODUIT_AVANCEMENT, Constantes.PRODUIT_COMMANDE_ID };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.PRODUIT_ID, getId_produit());
		content.put(Constantes.PRODUIT_TYPE, getType());
		content.put(Constantes.PRODUIT_ETAT, getEtat());
		content.put(Constantes.PRODUIT_MATERIEL, getMateriel());
		content.put(Constantes.PRODUIT_AVANCEMENT, getAvancement());
		content.put(Constantes.PRODUIT_COMMANDE_ID, getId_commande());

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
