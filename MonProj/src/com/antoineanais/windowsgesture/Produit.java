package com.antoineanais.windowsgesture;

public class Produit {

	/* Members */
	private int id_produit;
	
	private enum type{
		fenetre,
		porte
	};
	private enum etat{
		encours,
		valide,
		rebut,
		standby
	};
	private enum materiel{
		aluminium,
		acier,
		acierrenforce
	};
	private int avancement;
	
	/* Getters & Setters */
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getAvancement() {
		return avancement;
	}
	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}
	
	/* Construtors */
	public Produit() {
		super();
		this.avancement = 0;
	}
	
}
