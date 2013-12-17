package com.antoineanais.windowsgesture;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLite extends SQLiteOpenHelper {

	
	public DatabaseSQLite(Context context, String name, CursorFactory factory,
	int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create BDD
		db.execSQL(Constantes.CREATE_TABLE_USER);
		db.execSQL(Constantes.CREATE_TABLE_CLIENT);
		db.execSQL(Constantes.CREATE_TABLE_ZONE);
		db.execSQL(Constantes.CREATE_TABLE_PRODUIT);
		db.execSQL(Constantes.CREATE_TABLE_MACHINE);
		db.execSQL(Constantes.CREATE_TABLE_COMMANDE);
		db.execSQL(Constantes.CREATE_TABLE_LOG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stubEnable foreign key constraints
	}

	public void onConfigure(SQLiteDatabase db) {
		if (!db.isReadOnly()) {
			// enable foreign key
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}
}
