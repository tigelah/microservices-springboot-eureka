package br.com.rodrigo.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.rodrigo.moviecatalogservice.models.CatalogItem;
import br.com.rodrigo.moviecatalogservice.models.Movie;
import br.com.rodrigo.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	List<Rating> ratings = Arrays.asList(
    			new Rating("123",4),
    			new Rating("232",3));
    	
    	return ratings.stream().map(rating -> {
    		Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
    		return new CatalogItem(movie.getName(),"Desc",rating.getRating());
    		})
    	.collect(Collectors.toList());
    	
    	
        
    }
}
