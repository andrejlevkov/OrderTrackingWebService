package com.trackorder.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;
	@Column
	private float rating;
	@Column
	private int orderId;
	@Column
	private int userId;
	
	public Rating(float rating, int orderId, int userId) {
		this.rating = rating;
		this.orderId = orderId;
		this.userId = userId;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
		
}
