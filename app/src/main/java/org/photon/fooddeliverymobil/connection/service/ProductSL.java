package org.photon.fooddeliverymobil.connection.service;

import android.content.Context;
import android.util.Log;

import org.photon.fooddeliverymobil.connection.BaseGetAsyncTask;
import org.photon.fooddeliverymobil.connection.BaseGetServiceSL;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.ServiceResponseModel;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.ProductModel;
import org.photon.fooddeliverymobil.parser.ProductModelParser;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import java.util.List;


public class ProductSL extends BaseGetServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ProductSL.class.getSimpleName();

	private GetProductServiceListener<String> getProductServiceListener;

	public ProductSL(Context appContext, GetProductServiceListener<String> listener, String serviceResUri) {
		super(appContext, listener, serviceResUri);
		this.getProductServiceListener = listener;

	}
	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	public void setGetProductList(String postData){


		BaseGetAsyncTask<String> getAsyncTask = new BaseGetAsyncTask<>(context,NAME_OF_THE_CLASS,postData);
		getAsyncTask.addServiceClientListener(this);

		String uri = getServiceUri();
		getAsyncTask.execute(uri, HttpLink.getProductLink());

	}



	@Override public void onGetCommit(ResponseEventModel<String> responseEventModel) {
		String result = responseEventModel.getResponseData();

		if(responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getProductLink())){
			Log.d("prouctSL "," method name doğru");
			ServiceResponseModel model = FoodDeliveryUtils.getServiceResponseArrayModelDataKey(result);
			List<ProductModel> productListModelResponse = null;
			if (model != null) {
				productListModelResponse = new ProductModelParser().getProductList(model.getModel());
			}
			if (productListModelResponse == null) {
				//
				Log.d("product model ","ERROR");
			} else {
				getProductServiceListener.getProductList(productListModelResponse);
			}

		}


	}



}
