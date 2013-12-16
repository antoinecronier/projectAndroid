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
		getClientID(context, id);
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

	public void insertClient(Context context) {
		dataprovider data = new dataprovider(context);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { data.CLIENT_NOM };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(data.CLIENT_NOM, getNom());

		setId_client(db.insert(data.TABLE_NAME_CLIENT, null, content));
		
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
