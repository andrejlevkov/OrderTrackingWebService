package com.trackorder.restapi.entity;

public class GetOrdersInDeliveryResponseObject {
	private Order order;
	private long timeElapsed;
	
	public GetOrdersInDeliveryResponseObject(Order order, long timeElapsed) {
		this.order = order;
		this.timeElapsed = timeElapsed;
	}
	public GetOrdersInDeliveryResponseObject() {
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public long getTimeElapsed() {
		return timeElapsed;
	}
	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	
	
}
