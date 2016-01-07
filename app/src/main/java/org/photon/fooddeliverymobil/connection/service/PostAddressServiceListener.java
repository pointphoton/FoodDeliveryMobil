package org.photon.fooddeliverymobil.connection.service;

import org.photon.fooddeliverymobil.connection.BaseServiceListener;
import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.LogInUserModel;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public interface PostAddressServiceListener<T> extends BaseServiceListener<T> {

	void getAddressList(List<AddressModel> addressModels);
}
