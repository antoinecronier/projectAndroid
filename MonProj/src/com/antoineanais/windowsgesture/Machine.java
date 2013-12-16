package com.antoineanais.windowsgesture;

public class Machine {

	/*Members*/
	private int id_machine;
	private int id_zone;
	
	/*constructor*/
	public Machine(int id_zone) {
		super();
		this.id_zone = id_zone;
	}
	
	/*Getters & setters*/
	public int getId_machine() {
		return id_machine;
	}
	public void setId_machine(int id_machine) {
		this.id_machine = id_machine;
	}
	public int getId_zone() {
		return id_zone;
	}
	public void setId_zone(int id_zone) {
		this.id_zone = id_zone;
	}
}
