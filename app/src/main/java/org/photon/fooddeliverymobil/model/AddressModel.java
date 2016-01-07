package org.photon.fooddeliverymobil.model;

 public class AddressModel {

	 private Long customerId;
	 private Long addressId ;
	 private String addressDistrict;
	 private String addressText ;

	public AddressModel() {
	}

	 public AddressModel(Long customerId, Long addressId, String addressDistrict, String addressText) {
		 this.customerId = customerId;
		 this.addressId = addressId;
		 this.addressDistrict = addressDistrict;
		 this.addressText = addressText;
	 }

	 public Long getCustomerId() {
		return customerId;
	}

	public AddressModel setCustomerId(Long customerId) {
		this.customerId = customerId;
		return this;
	}

	 public Long getAddressId() {
		 return addressId;
	 }

	 public AddressModel setAddressId(Long addressId) {
		 this.addressId = addressId;
		 return this;
	 }

	 public String getAddressDistrict() {
		return addressDistrict;
	}

	public AddressModel setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
		return this;
	}

	public String getAddressText() {
		return addressText;
	}

	public AddressModel setAddressText(String addressText) {
		this.addressText = addressText;
		return this;
	}
}
