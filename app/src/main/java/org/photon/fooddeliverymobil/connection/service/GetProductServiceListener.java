package org.photon.fooddeliverymobil.connection.service;

import org.photon.fooddeliverymobil.connection.BaseServiceListener;
import org.photon.fooddeliverymobil.model.ProductModel;

import java.util.List;


public interface GetProductServiceListener<T> extends BaseServiceListener<T> {

	void getProductList(List<ProductModel> productListModelList);
}
