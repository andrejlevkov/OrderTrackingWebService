package com.trackorder.restapi.entity;

public class ChangeOrderLocationRequest {
	private int orderId;
	private String location;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ChangeOrderLocationRequest(int orderId, String location) {
		this.orderId = orderId;
		this.location = location;
	}
	public ChangeOrderLocationRequest() {
	}
	
}
