package com.example.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;

@RestController
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/catalog/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
			
		UserRating ratings=restTemplate.getForObject("http://rating-info-service/ratingsdata/users/rahul", UserRating.class);
				
		return ratings.getUserRating().stream().map(rating->{
		//for each movie id,call movie info service and get details
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/Foo", Movie.class);
		//put them all together
		return new CatalogItem(movie.getName(), "desc", rating.getRating());
		})
		.collect(Collectors.toList());
		
		//get all related movie ids
		
		
		
//		return Collections.singletonList(
//				new CatalogItem("transformers", "test", 4)	
//				);


//		  Movie movie= webClientBuilder.build()
//		  .get()
//		  .uri("http://localhost:8091/movies/"+rating.getMovieId())
//		  .retrieve()
//		  .bodyToMono(Movie.class)
//		  .block();
	}

}
