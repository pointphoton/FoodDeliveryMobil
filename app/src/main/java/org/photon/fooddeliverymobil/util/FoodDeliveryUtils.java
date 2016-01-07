package org.photon.fooddeliverymobil.util;

import android.util.Log;
import android.util.Pair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.photon.fooddeliverymobil.connection.ServiceResponseModel;

import java.util.List;


public class FoodDeliveryUtils {
	public static ServiceResponseModel getServiceResponseModel(String jsonResult, String dataObjectKey) {

		ServiceResponseModel model = new ServiceResponseModel();

		try {
			JsonElement jelement = new JsonParser().parse(jsonResult);
			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("productList");

			model.setModel(jobject.get(dataObjectKey).toString());
/*
			if (jobject != null) {

				if (!jobject.get(GlobalDataForWS.TOKEN.toString()).isJsonNull()) {
					model.setToken(jobject.get(GlobalDataForWS.TOKEN.toString()).toString());
				}

				if (!jobject.get(dataObjectKey).isJsonNull()) {
					model.setModel(jobject.get(dataObjectKey).toString());
				}
			}
			*/

		} catch (Exception e) {

		}

		return model;

	}

	public static ServiceResponseModel getServiceResponseModelDataKey(String jsonResult) {

		ServiceResponseModel model = new ServiceResponseModel();

		try {
			JsonElement jelement = new JsonParser().parse(jsonResult);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonElement element = jobject.get("productList");

			if (element != null) {

				model.setModel(element.toString());

			}

		} catch (Exception e) {
			Log.d("getServiceResponseModel", e.getMessage());
		}

		return model;

	}

	public static ServiceResponseModel getServiceResponseArrayModelDataKey(String jsonResult) {

		ServiceResponseModel model = new ServiceResponseModel();

		try {
			/*
			JsonElement jelement = new JsonParser().parse(jsonResult);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonElement element = jobject.get("productList");
			if (element != null) {

				model.setModel(element.toString());
				*/
			JsonElement jelement = new JsonParser().parse(jsonResult);
			   model.setModel(jelement.toString());


			}

		 catch (Exception e) {
			Log.d(" getServiceResponseArrayModelDataKey", e.getMessage());
		}

		return model;

	}

	public static String formattedData(List<Pair<String, String>> params) {

		if (params == null) {
			return null;
		}
		String retunValue = "{ ";

		if (params != null && params.size() > 0) {

			for (int i = 0; i < params.size(); i++) {

				if (params.size() > 1) {

					if (params.size() - 1 == i) {
						retunValue += "\"" + params.get(i).first + "\" : ";
						retunValue += "\"" + params.get(i).second + "\"";
					} else {
						retunValue += "\"" + params.get(i).first + "\" : ";
						retunValue += "\"" + params.get(i).second + "\"";
						retunValue += " , ";
					}

				} else {
					retunValue += "\"" + params.get(i).first + "\" : ";
					retunValue += "\"" + params.get(i).second + "\"";
				}

			}
		}
		retunValue += " }";
		return retunValue;
	}
}