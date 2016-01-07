package org.photon.fooddeliverymobil.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.OrderMessageModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class OrderModelParser {

	public OrderMessageModel getOrderMeassageModelFromJSON(String json) {
		OrderMessageModel model = null;
		try {
			Type type = new TypeToken<OrderMessageModel>() {
			}.getType();
			Gson gson = new Gson();
			model = gson.fromJson(json, type);
		} catch (Exception ex) {
			Log.d("getOrderMessage Parser", ex.getMessage());
		}
		return model;
	}
}
