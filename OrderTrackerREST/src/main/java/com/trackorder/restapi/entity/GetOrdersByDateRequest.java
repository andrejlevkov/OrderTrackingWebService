package com.trackorder.restapi.entity;

public class GetOrdersByDateRequest {
	private String date;

	public GetOrdersByDateRequest(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
