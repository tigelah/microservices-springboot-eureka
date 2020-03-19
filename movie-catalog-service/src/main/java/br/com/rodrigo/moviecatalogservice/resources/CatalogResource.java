package br.com.rodrigo.moviecatalogservice.resources;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import br.com.rodrigo.moviecatalogservice.models.CatalogItem;

import br.com.rodrigo.moviecatalogservice.models.UserRating;
import br.com.rodrigo.moviecatalogservice.services.MovieInfo;
import br.com.rodrigo.moviecatalogservice.services.UserRatingInfo;


@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;


	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating =  userRatingInfo.getUserRating(userId);

		return  userRating.getRatings().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());         
	}






	/*
        Alternative WebClient way
        Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
        .retrieve().bodyToMono(Movie.class).block();
	 */

}
