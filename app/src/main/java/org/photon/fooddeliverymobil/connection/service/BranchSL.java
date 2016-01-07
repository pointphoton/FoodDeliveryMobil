package org.photon.fooddeliverymobil.connection.service;

import android.content.Context;
import android.util.Log;

import org.photon.fooddeliverymobil.connection.BaseGetAsyncTask;
import org.photon.fooddeliverymobil.connection.BaseGetServiceSL;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.ServiceResponseModel;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.BranchModel;
import org.photon.fooddeliverymobil.model.ProductModel;
import org.photon.fooddeliverymobil.parser.BranchModelPasrer;
import org.photon.fooddeliverymobil.parser.ProductModelParser;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class BranchSL extends BaseGetServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ProductSL.class.getSimpleName();

	private GetBranchServiceListener<String> getBranchServiceListener;


	public BranchSL(Context appContext, GetBranchServiceListener<String> listener, String serviceResUri) {
		super(appContext, listener, serviceResUri);
		this.getBranchServiceListener =listener ;
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	public void setGetBranchList(String postData){


		BaseGetAsyncTask<String> getAsyncTask = new BaseGetAsyncTask<>(context,NAME_OF_THE_CLASS,postData);
		getAsyncTask.addServiceClientListener(this);

		String uri = getServiceUri();
		getAsyncTask.execute(uri, HttpLink.getBranchLink());

	}

	@Override public void onGetCommit(ResponseEventModel<String> responseEventModel) {
		String result = responseEventModel.getResponseData();

		if(responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getBranchLink())){
			Log.d("prouctSL ", " method name doğru");
			ServiceResponseModel model = FoodDeliveryUtils.getServiceResponseArrayModelDataKey(result);
			List<BranchModel> branchListModelResponse = null;
			if (model != null) {
				branchListModelResponse = new BranchModelPasrer().getBranchList(model.getModel());
			}
			if (branchListModelResponse == null) {
				//
				Log.d("branch model ","ERROR");
			} else {
				getBranchServiceListener.getBranchList(branchListModelResponse);
			}

		}

	}
}
