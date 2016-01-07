package org.photon.fooddeliverymobil.model;

/**
 * TODO: Add a class header comment!
 */
public class LogInUserModel {


private Long id;
	private String email;


	public LogInUserModel() {
	}

	public String getEmail() {
		return email;
	}

	public LogInUserModel setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getId() {
		return id;
	}

	public LogInUserModel setId(Long id) {
		this.id = id;
		return this;
	}
}
