package org.photon.fooddeliverymobil.application;

import android.app.Application;

import org.photon.fooddeliverymobil.model.OrderProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class FoodDeliveryApp extends Application {

	private static String customerId ;
	private static String email ;
	private static String token ;
	private static String branchId ;
	private  static String branchName ;
	private static List<OrderProductModel> sendProModels ;
private static String addressId ;
	@Override public void onCreate() {
		super.onCreate();
		sendProModels =new ArrayList<>();

	}

	public static String getCustomerId() {
		return customerId;
	}

	public static void setCustomerId(String customerId) {
		FoodDeliveryApp.customerId = customerId;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		FoodDeliveryApp.email = email;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		FoodDeliveryApp.token = token;
	}

	public static String getBranchId() {
		return branchId;
	}

	public static void setBranchId(String branchId) {
		FoodDeliveryApp.branchId = branchId;
	}

	public static String getBranchName() {
		return branchName;
	}

	public static void setBranchName(String branchName) {
		FoodDeliveryApp.branchName = branchName;
	}

	public static List<OrderProductModel> getSendProModels() {
		return sendProModels;
	}

	public static void setSendProModels(List<OrderProductModel> sendProModels) {
		FoodDeliveryApp.sendProModels = sendProModels;
	}

	public static String getAddressId() {
		return addressId;
	}

	public static void setAddressId(String addressId) {
		FoodDeliveryApp.addressId = addressId;
	}
}
