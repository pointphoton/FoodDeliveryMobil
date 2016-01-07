package org.photon.fooddeliverymobil.connection.service;

import org.photon.fooddeliverymobil.connection.BaseServiceListener;
import org.photon.fooddeliverymobil.model.BranchModel;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public interface GetBranchServiceListener<T> extends BaseServiceListener<T> {
	void getBranchList(List<BranchModel> branchModelList);
}
