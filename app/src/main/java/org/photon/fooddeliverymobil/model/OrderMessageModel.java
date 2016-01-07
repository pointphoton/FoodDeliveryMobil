package org.photon.fooddeliverymobil.model;

import java.util.Date;

/**
 * TODO: Add a class header comment!
 */
public class OrderMessageModel {

	private Long orderId ;
	private Double totalPrice ;
	private Date  orderTime;
	private String OrderMessage;

	public OrderMessageModel() {
	}

	public OrderMessageModel(Long orderId, Double totalPrice, Date orderTime, String orderMessage) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.orderTime = orderTime;
		OrderMessage = orderMessage;
	}

	public Long getOrderId() {
		return orderId;
	}

	public OrderMessageModel setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public OrderMessageModel setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public OrderMessageModel setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
		return this;
	}

	public String getOrderMessage() {
		return OrderMessage;
	}

	public OrderMessageModel setOrderMessage(String orderMessage) {
		OrderMessage = orderMessage;
		return this;
	}

}
