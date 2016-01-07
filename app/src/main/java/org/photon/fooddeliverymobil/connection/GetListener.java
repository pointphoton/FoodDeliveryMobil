package org.photon.fooddeliverymobil.connection;

import java.util.EventListener;

/**
 * TODO: Add a class header comment!
 */
public interface GetListener<T> extends EventListener {

	void onGetCommit(ResponseEventModel<T> responseEventModel);
}
