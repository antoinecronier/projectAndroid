package com.antoineanais.windowsgesture;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Définit un utilisateur de l'application
 * 
 * @author alexandre
 * 
 */
public class User implements Serializable{

	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = -3185036184140455157L;
	
	/* Members */
	private int id_user;
	private String login;
	private String password;
	private int role;

	/* constructor */
	public User(String login, String password, int role) {
		super();
		/* 0 ne représente rien vis à vis de l'ID et du role */
		this.id_user = 0;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(Context context, int id_user, SQLiteDatabase db) {
		super();
		this.id_user = id_user;
		getUserByIDForLog(context, db);
	}

	/* Getters & setters */
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	/* Méthodes */

	/**
	 * Insert un user en base et set son id
	 * 
	 * @param context
	 */
	public void insertProduit(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		String[] COL = { Constantes.USER_LOGIN, Constantes.USER_PWD,
				Constantes.USER_ROLE };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.USER_LOGIN, getLogin());
		content.put(Constantes.USER_PWD, getPassword());
		content.put(Constantes.USER_ROLE, getRole());

		setId_user((int) db.insert(Constantes.TABLE_NAME_USER, null, content));

		db.close();
	}

	/**
	 * Récupère le user depuis son ID
	 * 
	 * @param context
	 * @param ID
	 */
	public void getUserByID(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.USER_ID, Constantes.USER_LOGIN,
				Constantes.USER_PWD, Constantes.USER_ROLE };
		String WHERE = Constantes.USER_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_user()) };

		monCu = db.query(Constantes.TABLE_NAME_USER, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setLogin(monCu.getString(1));
				setPassword(monCu.getString(2));
				setRole(monCu.getInt(3));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère le login mot de passe pour authentifier l'utilisateur
	 * 
	 * @param context
	 * @param ID
	 */
	public void getUserForAuthnetification(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		Cursor monCu;

		String[] COL = { Constantes.USER_ID, Constantes.USER_LOGIN,
				Constantes.USER_PWD, Constantes.USER_ROLE };
		String WHERE = Constantes.USER_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getLogin()),
				String.valueOf(getPassword()) };

		monCu = db.query(Constantes.TABLE_NAME_USER, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst() && monCu.getCount() > 0) {
			do {
				setId_user(monCu.getInt(0));
				setLogin(monCu.getString(1));
				setPassword(monCu.getString(2));
				setRole(monCu.getInt(3));
			} while (monCu.moveToNext());
		}
		db.close();
	}

	/**
	 * Récupère le user depuis son ID pour les listes de logs
	 * 
	 * @param context
	 * @param db
	 */
	public void getUserByIDForLog(Context context, SQLiteDatabase db) {
		Cursor monCu;

		String[] COL = { Constantes.USER_ID, Constantes.USER_LOGIN,
				Constantes.USER_PWD, Constantes.USER_ROLE };
		String WHERE = Constantes.USER_ID + " = ?";
		String[] CLAUSE = { String.valueOf(getId_user()) };

		monCu = db.query(Constantes.TABLE_NAME_USER, COL, WHERE, CLAUSE, null,
				null, null);
		if (monCu.moveToFirst()) {
			do {
				setLogin(monCu.getString(1));
				setPassword(monCu.getString(2));
				setRole(monCu.getInt(3));
			} while (monCu.moveToNext());
		}
	}

	/**
	 * Mise a jour des données du user
	 * 
	 * @param context
	 * @return
	 */
	public int upDateUser(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getWritableDatabase();

		int toReturn = 0;
		String[] COL = { Constantes.USER_ID, Constantes.USER_LOGIN,
				Constantes.USER_PWD, Constantes.USER_ROLE };
		String[] WHERE = {};

		ContentValues content = new ContentValues();

		content.put(Constantes.USER_LOGIN, getLogin());
		content.put(Constantes.USER_PWD, getPassword());
		content.put(Constantes.USER_ROLE, getRole());

		toReturn += db.update(Constantes.TABLE_NAME_USER, content,
				Constantes.USER_ID + " = ?",
				new String[] { String.valueOf(getId_user()) });

		db.close();

		return toReturn;
	}

	/**
	 * Suppression du produit
	 * 
	 * @param context
	 */
	public void deletedUser(Context context) {
		DatabaseSQLite data = new DatabaseSQLite(context,
				Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
		SQLiteDatabase db = data.getReadableDatabase();

		db.delete(Constantes.TABLE_NAME_USER, Constantes.USER_ID + " = ?",
				new String[] { String.valueOf(getId_user()) });

		db.close();
	}
}
