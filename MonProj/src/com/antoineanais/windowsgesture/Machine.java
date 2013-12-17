package com.antoineanais.windowsgesture;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Machine {

	/* Members */
	private int id_machine;
	private String nom;
	private Zone zone;

	/* Getters & setters */
	public int getId_machine() {
		return id_machine;
	}

	public void setId_machine(int id_machine) {
		this.id_machine = id_machine;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	/* constructor */
	public Machine() {
		super();
	}
	public Machine(Context context, int id_machine) {
		super();
		this.id_machine = id_machine;
		getProduitByID(context);
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

		String[] COL = { Constantes.MACHINE_ZONE_ID, Constantes.MACHINE_NAME };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.MACHINE_ZONE_ID, getZone().getId_zone());
		content.put(Constantes.MACHINE_NAME, getNom());

		setId_machine((int) db.insert(Constantes.TABLE_NAME_MACHINE, null,
				content));

		db.close();
	}

	/**
	 * Récupère la machine depuis son ID
	 * 
	 * @param context
	 * @param ID
	 */
	public void getMachineByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.MACHINE_ID, Constantes.MACHINE_ZONE_ID,
				Constantes.MACHINE_NAME };
		String WHERE = Constantes.MACHINE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_machine()) };

		monCu = db.query(Constantes.TABLE_NAME_MACHINE, COL, WHERE, CLAUSE,
				null, null, null);
		if (monCu.moveToFirst()) {
			do {
				setZone(new Zone(monCu.getInt(1)));
				setNom(monCu.getString(2));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère l'ensemble des logs pour une machine
	 * @param context
	 * @param ID
	 * @return
	 */
	public ArrayList<Commande> getLogstByMachinetID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.MACHINE_ID, Constantes.MACHINE_ZONE_ID,
				Constantes.MACHINE_NAME };
		String WHERE = Constantes.MACHINE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_machine()) };

		monCu = db.query(Constantes.TABLE_NAME_MACHINE, COL, WHERE, CLAUSE,
				null, null, null);
		
		ArrayList<Commande> ListCommandes = new ArrayList<Commande>();
		
		if (monCu.moveToFirst()) {
			do {
				Commande uneCommande = new Commande();
				uneCommande.setId_cmd(monCu.getInt(0));
				uneCommande.setClient(new Client(context, ID));
				uneCommande.setDateCreation(monCu.getString(2));
				uneCommande.setDateFin(monCu.getString(3));
				uneCommande.setDateLivraison(monCu.getString(4));
				uneCommande.setAvancement(monCu.getInt(5));
				//TODO check for produits
				ListCommandes.add(uneCommande);
			} while (monCu.moveToNext());
		}
		db.close();
		return ListCommandes;
	}
	/**
	 * Mise a jour des données de la machine
	 * 
	 * @param context
	 * @return
	 */
	public int upDateClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.MACHINE_ID, Constantes.MACHINE_ZONE_ID,
				Constantes.MACHINE_NAME };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.MACHINE_ID, getId_machine());
		content.put(Constantes.MACHINE_ZONE_ID, getZone().getId_zone());
		content.put(Constantes.MACHINE_NAME, getNom());

		toReturn += db.update(Constantes.TABLE_NAME_MACHINE, content,
				Constantes.MACHINE_ID + " = ?",
				new String[] { String.valueOf(getId_machine()) });

		db.close();

		return toReturn;
	}

	/**
	 * Suppression de la machine
	 * 
	 * @param context
	 */
	public void deletedMachine(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_MACHINE,
				Constantes.MACHINE_ID + " = ?",
				new String[] { String.valueOf(getId_machine()) });

		db.close();
	}
}
