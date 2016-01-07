package org.photon.fooddeliverymobil.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

	public class ProductModel {

		@SerializedName("productId") @Expose private Long productId;
		@SerializedName("proName") @Expose private String name;
		@SerializedName("proDesc") @Expose private String description;
		@SerializedName("currentPrice") @Expose private Double currentPrice;

		public ProductModel() {
		}

		public ProductModel(Long productId, Double currentPrice, String description, String name) {
			this.productId = productId;
			this.currentPrice = currentPrice;
			this.description = description;
			this.name = name;
		}

		public Long getProductId() {
			return productId;
		}

		public ProductModel setProductId(Long productId) {
			this.productId = productId;
			return this;
		}

		public String getName() {
			return name;
		}

		public ProductModel setName(String name) {
			this.name = name;
			return this;
		}

		public String getDescription() {
			return description;
		}

		public ProductModel setDescription(String description) {
			this.description = description;
			return this;
		}

		public Double getCurrentPrice() {
			return currentPrice;
		}

		public ProductModel setCurrentPrice(Double currentPrice) {
			this.currentPrice = currentPrice;
			return this;
		}
	}