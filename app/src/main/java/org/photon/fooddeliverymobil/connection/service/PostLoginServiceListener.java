package org.photon.fooddeliverymobil.connection.service;

import org.photon.fooddeliverymobil.connection.BaseServiceListener;
import org.photon.fooddeliverymobil.model.LogInUserModel;

/**
 * TODO: Add a class header comment!
 */
public interface PostLoginServiceListener<T> extends BaseServiceListener<T>  {

	void logInAccount(LogInUserModel logInUserModel);
}
