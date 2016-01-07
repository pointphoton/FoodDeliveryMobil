package org.photon.fooddeliverymobil.connection;

import android.content.Context;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class BaseGetServiceSL<T> implements GetListener<T> {


	protected Context context;
	private String serviceUri;
	private List<BaseServiceListener<T>> listeners ;
	private BaseServiceListener<T> serviceListener;



	public BaseGetServiceSL(Context appContext, BaseServiceListener<T> listener, String serviceResUri) {
		// Base Constructor
		this.listeners = new CopyOnWriteArrayList<>();
		this.serviceUri = "";
		this.context=appContext;
		this.serviceUri = serviceResUri;
		this.serviceListener = listener;
		addListener(serviceListener);

	}



	public synchronized void addListener(BaseServiceListener<T> listener) {
		this.listeners.add(listener);
	}



	public String getServiceUri() {
		return serviceUri;
	}




}
