package org.photon.fooddeliverymobil.connection.service;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import org.photon.fooddeliverymobil.connection.BasePostAsyncTask;
import org.photon.fooddeliverymobil.connection.BasePostServiceSL;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.ServiceResponseModel;
import org.photon.fooddeliverymobil.enums.GlobalDataForWS;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.SendAddressModel;
import org.photon.fooddeliverymobil.parser.AddressModelParser;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class AddressSL  extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = AddressSL.class.getSimpleName();
	PostAddressServiceListener<String> addressServiceListener;

	public AddressSL(Context appContext, PostAddressServiceListener<String> listener, String serviceResUri) {
		super(appContext,listener,serviceResUri);
		addressServiceListener = listener;


	}


	public void sendAddressInfo(SendAddressModel sendAddressModel){
		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), sendAddressModel.getId()));
		params.add(new Pair(GlobalDataForWS.EMAIL.toString(), sendAddressModel.getEmail()));
		params.add(new Pair(GlobalDataForWS.TOKEN.toString(), sendAddressModel.getToken()));

		String postData = FoodDeliveryUtils.formattedData(params);
		// Post service CALL
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, HttpLink.getAddressLink());

	}

	@Override public void onPostCommit(ResponseEventModel<String> responseEventModel) {

		String result = responseEventModel.getResponseData();
		if (responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getAddressLink())) {
			Log.d("AdressSL ", " method name doğru");
			ServiceResponseModel model = FoodDeliveryUtils.getServiceResponseArrayModelDataKey(result);

			if (model != null) {

				AddressModelParser addressModelParserJSON = new AddressModelParser();
				List<AddressModel> addressModelResponse = addressModelParserJSON.getAddressModelFromJSON(model.getModel());
				if (addressModelResponse == null) {

					//logging...
					Log.d("Address model ", "ERROR");
				} else {
					addressServiceListener.getAddressList(addressModelResponse);
				}

			}
		}

	}
}
