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
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { data.COMMANDE_ClIENT_ID, data.COMMANDE_DATECREATION,
				data.COMMANDE_DATEFIN, data.COMMANDE_DATELIVRAISON,
				data.COMMANDE_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(data.COMMANDE_ClIENT_ID, getClient().getId_client());
		content.put(data.COMMANDE_DATECREATION, getDateCreation());
		content.put(data.COMMANDE_DATEFIN, getDateFin());
		content.put(data.COMMANDE_DATELIVRAISON, getDateLivraison());
		content.put(data.COMMANDE_AVANCEMENT, getAvancement());

		setId_cmd(db.insert(data.TABLE_NAME_COMMANDE, null, content));

		db.close();
	}

	public void getClientID(Context context, int ID) {
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { data.CLIENT_ID, data.CLIENT_NOM };
		String WHERE = data.CLIENT_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_client()) };

		monCu = db.query(data.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setNom(monCu.getString(1));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	public void getClientNom(Context context) {
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { data.CLIENT_ID, data.CLIENT_NOM };
		String WHERE = data.CLIENT_NOM + " = ?";
		String[] CLAUSE = { getNom() };

		monCu = db.query(data.TABLE_NAME_CLIENT, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setId_client(monCu.getInt(0));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	public int upDateClient(Context context) {
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { data.CLIENT_ID, data.CLIENT_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(data.CLIENT_ID, getId_client());
		content.put(data.CLIENT_NOM, getNom());

		toReturn += db.update(data.TABLE_NAME_CLIENT, content, data.CLIENT_ID
				+ " = ?", new String[] { String.valueOf(getId_client()) });

		db.close();

		return toReturn;
	}

	public void deletedClient(Context context) {
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(data.TABLE_NAME_CLIENT, data.CLIENT_ID + " = ?",
				new String[] { getId_client() });

		db.close();
	}
}
