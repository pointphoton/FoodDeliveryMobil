package org.photon.fooddeliverymobil.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.LogInUserModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class AddressModelParser {

	public List<AddressModel> getAddressModelFromJSON(String json) {
		List<AddressModel> model = null;
		try {
			Type type = new TypeToken<List<AddressModel>>() {
			}.getType();
			Gson gson = new Gson();
			model = gson.fromJson(json, type);
		} catch (Exception ex) {
			Log.d("getAddressModel Parser", ex.getMessage());
		}
		return model;
	}
}
