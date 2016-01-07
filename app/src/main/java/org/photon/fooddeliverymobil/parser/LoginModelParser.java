package org.photon.fooddeliverymobil.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.photon.fooddeliverymobil.model.LogInUserModel;

import java.lang.reflect.Type;

/**
 * TODO: Add a class header comment!
 */
public class LoginModelParser {

	public LogInUserModel getloginUserModelFromJSON(String json) {
		LogInUserModel model = null;
		try {
			Type type = new TypeToken<LogInUserModel>() {
			}.getType();
			Gson gson = new Gson();
			model = gson.fromJson(json, type);
		} catch (Exception ex) {
			Log.d("getloginUserModel Parser", ex.getMessage());
		}
		return model;
	}
}
