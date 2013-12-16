package com.antoineanais.windowsgesture;

public class User {

	/*Members*/
	private int id_user;
	private String login;
	private String password;
	private int role;
	
	/*constructor*/
	public User(String login, String password, int role) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	/*Getters & setters*/
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
}
