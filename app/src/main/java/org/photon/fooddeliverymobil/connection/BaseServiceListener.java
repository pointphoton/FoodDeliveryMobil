package org.photon.fooddeliverymobil.connection;

import java.util.EventListener;


public interface BaseServiceListener<TModel> extends EventListener {

	public void onComplete(ResponseEventModel<TModel> onComplete);


}