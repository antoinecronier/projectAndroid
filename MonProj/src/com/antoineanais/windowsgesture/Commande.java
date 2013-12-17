package com.antoineanais.windowsgesture;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Commande {

	/* Members */
	private int id_cmd;
	private Client client;
	private String dateCreation;
	private String dateFin;
	private String dateLivraison;
	private int avancement;
	private ArrayList<Produit> produits;

	/* constructor */
	public Commande(int id_client, String dateCreation, String dateFin,
			String dateLivraison, int avancement, ArrayList<Produit> produits,
			Context context) {
		super();
		this.client = new Client(context, id_client);
		this.dateCreation = dateCreation;
		this.dateFin = dateFin;
		this.dateLivraison = dateLivraison;
		this.avancement = avancement;
		this.produits = produits;
	}

	/* Getters & setters */
	public int getId_cmd() {
		return id_cmd;
	}

	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public int getAvancement() {
		return avancement;
	}

	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public void insertCommand(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context, 
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.COMMANDE_CLIENT_ID, 
				Constantes.COMMANDE_DATECREATION, Constantes.COMMANDE_DATEFIN, 
				Constantes.COMMANDE_DATELIVRAISON,
				Constantes.COMMANDE_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.COMMANDE_CLIENT_ID, getClient().getId_client());
		content.put(Constantes.COMMANDE_DATECREATION, getDateCreation());
		content.put(Constantes.COMMANDE_DATEFIN, getDateFin());
		content.put(Constantes.COMMANDE_DATELIVRAISON, getDateLivraison());
		content.put(Constantes.COMMANDE_AVANCEMENT, getAvancement());

		setId_cmd((int) db.insert(Constantes.TABLE_NAME_COMMANDE, null, 
				content));

		db.close();
	}

	public void getClientID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String WHERE = Constantes.CLIENT_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getClient().getId_client()) };

		monCu = db.query(Constantes.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				getClient().setNom(monCu.getString(1));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	public void getClientNom(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String WHERE = Constantes.CLIENT_NOM + " = ?";
		String[] CLAUSE = { getClient().getNom() };

		monCu = db.query(Constantes.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				getClient().setId_client(monCu.getInt(0));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	public int upDateClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.CLIENT_ID, Constantes.CLIENT_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.CLIENT_ID, getClient().getId_client());
		content.put(Constantes.CLIENT_NOM, getClient().getNom());

		toReturn += db.update(Constantes.TABLE_NAME_CLIENT, content, 
				Constantes.CLIENT_ID+ " = ?", 
				new String[] { String.valueOf(getClient().getId_client()) });

		db.close();

		return toReturn;
	}

	public void deletedClient(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_CLIENT, Constantes.CLIENT_ID + " = ?",
				new String[] { getClient().getId_client() });

		db.close();
	}
}
