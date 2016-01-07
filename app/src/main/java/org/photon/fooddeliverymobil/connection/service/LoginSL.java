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
import org.photon.fooddeliverymobil.model.LogInUserModel;
import org.photon.fooddeliverymobil.model.LoginModel;
import org.photon.fooddeliverymobil.parser.LoginModelParser;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class LoginSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = LoginSL.class.getSimpleName();
	PostLoginServiceListener<String> loginServiceListener;

	public LoginSL(Context appContext, PostLoginServiceListener<String> listener, String serviceResUri) {
		super(appContext,listener,serviceResUri);
		loginServiceListener = listener;


	}

	public void setLogInAccount(LoginModel loginModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.EMAIL.toString(), loginModel.getEmail()));
		params.add(new Pair(GlobalDataForWS.PASSWORD.toString(), loginModel.getPassword()));
		String postData = FoodDeliveryUtils.formattedData(params);
		// Post service CALL
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, HttpLink.getLoginLink());
	}

	@Override public void onPostCommit(ResponseEventModel<String> responseEventModel) {

		String result = responseEventModel.getResponseData();
		if (responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getLoginLink())) {
			Log.d("loginSL "," method name doğru");
			ServiceResponseModel model = FoodDeliveryUtils.getServiceResponseArrayModelDataKey(result);

			if (model != null) {

				LoginModelParser loginModelParserJSON = new LoginModelParser();
				LogInUserModel logInUserModelResponse = loginModelParserJSON.getloginUserModelFromJSON(model.getModel());
				if (logInUserModelResponse == null) {

					//logging...
					Log.d("login model ", "ERROR");
				} else {
					loginServiceListener.logInAccount(logInUserModelResponse);
				}

			}
		}

	}
}
