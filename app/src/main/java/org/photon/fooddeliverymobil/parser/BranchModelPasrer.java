package org.photon.fooddeliverymobil.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.photon.fooddeliverymobil.model.BranchModel;
import org.photon.fooddeliverymobil.model.ProductModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class BranchModelPasrer {

	public List<BranchModel> getBranchList(String json) {

		List<BranchModel> model = null;
		try {
			Type type = new TypeToken<List<BranchModel>>() {
			}.getType();

			model = new Gson().fromJson(json, type);

		} catch (Exception ex) {
			Log.d("getBranchList JSON", ex.getMessage());
		}

		return model;
	}
}
