package org.photon.fooddeliverymobil.model;

/**
 * TODO: Add a class header comment!
 */
public class SendAddressModel {

private String id ;
	private String email;
	private String token ;

	public SendAddressModel() {
	}

	public SendAddressModel(String id, String email, String token) {
		this.id = id;
		this.email = email;
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public SendAddressModel setId(String id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public SendAddressModel setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getToken() {
		return token;
	}

	public SendAddressModel setToken(String token) {
		this.token = token;
		return this;
	}
}
