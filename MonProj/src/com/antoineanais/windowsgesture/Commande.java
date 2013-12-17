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

	public Commande() {
		super();
		this.client = new Client();
		this.dateCreation = "";
		this.dateFin = "";
		this.dateLivraison = "";
		this.avancement = 0;
		this.produits = new ArrayList<Produit>();
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

	/* Méthodes */
	
	/**
	 * Insert une commande et set son id
	 * @param context
	 */
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

		setId_cmd((int) db
				.insert(Constantes.TABLE_NAME_COMMANDE, null, content));

		db.close();
	}

	/**
	 * Récupère une commande par l'ID
	 * @param context
	 * @param ID
	 */
	public void getCommandeByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.COMMANDE_ID, Constantes.COMMANDE_CLIENT_ID,
				Constantes.COMMANDE_DATECREATION, Constantes.COMMANDE_DATEFIN,
				Constantes.COMMANDE_DATELIVRAISON,
				Constantes.COMMANDE_AVANCEMENT };
		
		String WHERE = Constantes.COMMANDE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_cmd()) };

		monCu = db.query(Constantes.TABLE_NAME_COMMANDE, COL, WHERE, CLAUSE,
				null, null, null);
		if (monCu.moveToFirst()) {
			do {
				setDateCreation(monCu.getString(1));
				setDateFin(monCu.getString(2));
				setDateLivraison(monCu.getString(3));
				setAvancement(monCu.getInt(4));
				//TODO check for produits
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère l'ensemble des commandes pour un client
	 * @param context
	 * @param ID
	 * @return
	 */
	public ArrayList<Commande> getCommandetByClientID(Context context, int ID) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.COMMANDE_ID, Constantes.COMMANDE_CLIENT_ID,
				Constantes.COMMANDE_DATECREATION, Constantes.COMMANDE_DATEFIN,
				Constantes.COMMANDE_DATELIVRAISON,
				Constantes.COMMANDE_AVANCEMENT };
		String WHERE = Constantes.COMMANDE_ID + " = ?";
		String[] CLAUSE = { String.valueOf(ID) };

		monCu = db.query(Constantes.TABLE_NAME_COMMANDE, COL, WHERE, CLAUSE,
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
	 * Met à jour la commande
	 * @param context
	 * @return
	 */
	public int upDateCommande(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.COMMANDE_ID, Constantes.COMMANDE_CLIENT_ID,
				Constantes.COMMANDE_DATECREATION, Constantes.COMMANDE_DATEFIN,
				Constantes.COMMANDE_DATELIVRAISON,
				Constantes.COMMANDE_AVANCEMENT };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.COMMANDE_ID, getId_cmd());
		content.put(Constantes.COMMANDE_CLIENT_ID, getClient().getId_client());
		content.put(Constantes.COMMANDE_DATECREATION, getDateCreation());
		content.put(Constantes.COMMANDE_DATEFIN, getDateFin());
		content.put(Constantes.COMMANDE_DATELIVRAISON, getDateLivraison());
		content.put(Constantes.COMMANDE_AVANCEMENT, getAvancement());

		toReturn += db.update(Constantes.TABLE_NAME_COMMANDE, content,
				Constantes.COMMANDE_ID + " = ?",
				new String[] { String.valueOf(getId_cmd())});

		db.close();

		return toReturn;
	}

	/**
	 * Supprime la commande
	 * @param context
	 */
	public void deletedCommande(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_COMMANDE, Constantes.COMMANDE_ID + " = ?",
				new String[] { String.valueOf(getId_cmd())});

		db.close();
	}
}
