package org.photon.fooddeliverymobil.connection.service;

import org.photon.fooddeliverymobil.connection.BaseServiceListener;
import org.photon.fooddeliverymobil.model.OrderMessageModel;

/**
 * TODO: Add a class header comment!
 */
public interface PostOrderServiceListener<T> extends BaseServiceListener<T> {


	void getOrderMessage(OrderMessageModel orderMessageModel);
}
