package org.photon.fooddeliverymobil.connection;

import java.util.EventListener;

/**
 * TODO: Add a class header comment!
 */
public interface PostListener<T> extends EventListener {

		void onPostCommit(ResponseEventModel<T> responseEventModel);
}
