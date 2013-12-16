package com.antoineanais.windowsgesture;

public class Log {
	/* Members */
	private int id_log;
	private int id_produit;
	private int id_machine;
	private String duree;
	private String dateEntre;
	private String dateSortie;

	/* Getters & Setters */
	public int getId_log() {
		return id_log;
	}
	public void setId_log(int id_log) {
		this.id_log = id_log;
	}
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getId_machine() {
		return id_machine;
	}
	public void setId_machine(int id_machine) {
		this.id_machine = id_machine;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getDateEntre() {
		return dateEntre;
	}
	public void setDateEntre(String dateEntre) {
		this.dateEntre = dateEntre;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	/* construtors */
	public Log(String duree, String dateEntre) {
		super();
		this.duree = duree;
		this.dateEntre = dateEntre;
	}
}
