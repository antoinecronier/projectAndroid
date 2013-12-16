package com.antoineanais.windowsgesture;

public class Client {
	
	/*Members*/
	private int id_client;
	private String nom;
	
	/*constructor*/
	public Client(String nom) {
		super();
		this.nom = nom;
	}
	
	/*Getters & setters*/
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
}
