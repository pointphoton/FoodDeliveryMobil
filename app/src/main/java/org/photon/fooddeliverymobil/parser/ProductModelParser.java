package org.photon.fooddeliverymobil.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.photon.fooddeliverymobil.model.ProductModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class ProductModelParser {
	public List<ProductModel> getProductList(String json) {

		List<ProductModel> model = null;
		try {
			Type type = new TypeToken<List<ProductModel>>() {
			}.getType();

			model = new Gson().fromJson(json, type);

		} catch (Exception ex) {
			Log.d("getProductList JSON", ex.getMessage());
		}

		return model;
	}
}
