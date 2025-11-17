package com.trackorder.restapi.entity;

public class ChangeOrderStatusRequest {
	private String status;
	private int orderId;

	public ChangeOrderStatusRequest(String status, int orderId) {
		this.status = status;
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return orderId;
	}

	public void setId(int id) {
		this.orderId = id;
	}
}
