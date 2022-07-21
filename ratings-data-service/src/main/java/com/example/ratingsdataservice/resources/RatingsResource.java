package com.example.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;

@RestController
public class RatingsResource {
	
	@GetMapping("/ratingsdata/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@GetMapping("/ratingsdata/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		List<Rating> ratings=Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3)	
		);
		UserRating userRating= new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
