package com.antoineanais.windowsgesture;

public class Zone {

	/*Members*/
	private int id_zone;
	private  String nom;
	
	/*Getters & setters*/
	public int getId_zone() {
		return id_zone;
	}
	public void setId_zone(int id_zone) {
		this.id_zone = id_zone;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/*constructor*/
	public Zone(int id_zone) {
		super();
		this.id_zone = id_zone;
	}
}
