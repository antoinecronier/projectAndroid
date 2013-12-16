package com.antoineanais.windowsgesture;

import java.util.ArrayList;

public class Commande {

	/*Members*/
	private int id_cmd;
	private int id_client;
	private String dateCreation;
	private String dateFin;
	private String dateLivraison;
	private int avancement;
	private ArrayList produits;
	
	/*constructor*/
	public Commande(int id_client, String dateCreation, String dateFin,
			String dateLivraison, int avancement) {
		super();
		this.id_client = id_client;
		this.dateCreation = dateCreation;
		this.dateFin = dateFin;
		this.dateLivraison = dateLivraison;
		this.avancement = avancement;
	}
	
	/*Getters & setters*/
	public int getId_cmd() {
		return id_cmd;
	}
	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
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
}
