package org.photon.fooddeliverymobil.model;

/**
 * TODO: Add a class header comment!
 */
public class BranchModel {

	private Long branchId;
	private String branchName;

	public Long getBranchId() {
		return branchId;
	}

	public BranchModel setBranchId(Long branchId) {
		this.branchId = branchId;
		return this;
	}

	public String getBranchName() {
		return branchName;
	}

	public BranchModel setBranchName(String branchName) {
		this.branchName = branchName;
		return this;
	}
}
