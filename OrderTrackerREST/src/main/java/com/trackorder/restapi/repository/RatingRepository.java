package com.trackorder.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorder.restapi.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	@Query(value="SELECT * FROM ratings WHERE orderId = ?1", nativeQuery = true)
	List<Rating> findOrderRatings(int orderId);
}
