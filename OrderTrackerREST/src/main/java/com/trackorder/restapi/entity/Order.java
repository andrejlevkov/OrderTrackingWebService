package com.trackorder.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private int orderId;
	@Column
	private String status;
	@Column
	private String location;
	@Column
	private String created;
	@Column
	private String departure;
	@Column
	private String collected;
	
	public Order() {
		
	}
	
	public Order(String status, String location, String departure, String collected) {
		this.status = status;
		this.location = location;
		this.departure = departure;
		this.collected = collected;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getCollected() {
		return collected;
	}
	public void setCollected(String collected) {
		this.collected = collected;
	}
	
	
}
