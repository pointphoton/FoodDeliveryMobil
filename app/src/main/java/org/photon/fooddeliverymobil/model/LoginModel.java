package org.photon.fooddeliverymobil.model;

/**
 * TODO: Add a class header comment!
 */
public class LoginModel {

	String email ;
	String password;


	public LoginModel() {
	}

	public LoginModel(String mail , String pass) {
		this.email = mail;
		this.password = pass;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
