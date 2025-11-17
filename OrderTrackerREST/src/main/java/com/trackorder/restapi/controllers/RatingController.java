package com.trackorder.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trackorder.restapi.entity.Rating;
import com.trackorder.restapi.repository.RatingRepository;

@RestController
public class RatingController {
	
	@Autowired
	RatingRepository ratingRepository;
	
	@CrossOrigin(origins = "http://localhost:5050")
	@PostMapping("/ratings/add/")
	public void addRating(@RequestBody Rating rating) {
		ratingRepository.save(rating);
	}
	
	@CrossOrigin(origins = "http://localhost:5050")
	@GetMapping("/ratings/{id}")
	public List<Rating> getOrderRatings(@PathVariable int id){
		List<Rating> ratings = ratingRepository.findOrderRatings(id);
		
		return ratings;
	}
}
