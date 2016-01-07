package org.photon.fooddeliverymobil.connection.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.photon.fooddeliverymobil.connection.BasePostAsyncTask;
import org.photon.fooddeliverymobil.connection.BasePostServiceSL;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.ServiceResponseModel;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.OrderCompleteModel;
import org.photon.fooddeliverymobil.model.OrderMessageModel;
import org.photon.fooddeliverymobil.parser.AddressModelParser;
import org.photon.fooddeliverymobil.parser.OrderModelParser;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class OrderSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = OrderSL.class.getSimpleName();
	PostOrderServiceListener<String> orderServiceListener;

	public OrderSL(Context appContext, PostOrderServiceListener<String> listener, String serviceResUri) {
		super(appContext, listener, serviceResUri);
		orderServiceListener = listener;

	}

	public void sendOrder(OrderCompleteModel orderCompleteModel) {

		Gson gson = new Gson();
		Log.d("order json ", gson.toJson(orderCompleteModel));
		//JsonObject json = (JsonObject)parser.parse(new Gson().toJson(orderCompleteModel));
		String postData = gson.toJson(orderCompleteModel).toString();
		Log.d("order postData ", postData);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, HttpLink.getOrderLink());
	}

	@Override public void onPostCommit(ResponseEventModel<String> responseEventModel) {
		String result = responseEventModel.getResponseData();
		if (responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getOrderLink())) {
			Log.d("OrderSL ", " method name doğru");
			ServiceResponseModel model = FoodDeliveryUtils.getServiceResponseArrayModelDataKey(result);

			if (model != null) {

				OrderModelParser orderModelParserJSON = new OrderModelParser();
				OrderMessageModel orderMessageModelResponse = orderModelParserJSON.getOrderMeassageModelFromJSON(model.getModel());
				if (orderMessageModelResponse == null) {

					//logging...
					Log.d("Order model ", "ERROR");
				} else {
					Log.d("order response", orderMessageModelResponse.getOrderMessage()+" ");
					Log.d("order response", orderMessageModelResponse.getOrderId()+" ");
					Log.d("order response", orderMessageModelResponse.getOrderTime()+" ");
					Log.d("order response", orderMessageModelResponse.getTotalPrice()+" ");
					orderServiceListener.getOrderMessage(orderMessageModelResponse);
				}

			}

		}
	}
}