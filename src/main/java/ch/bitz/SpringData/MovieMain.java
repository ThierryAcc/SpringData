package ch.bitz.SpringData;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import ch.bitz.SpringData.data.Actor;
import ch.bitz.SpringData.data.Movie;
import ch.bitz.SpringData.data.MovieRepository;

@Component
public class MovieMain {

	@Autowired
	MovieRepository movieRepo;

	@Transactional
	public void call() {
		Movie movie1 = new Movie();
		movie1.setTitle("Inception");
		movie1.setDescription("BLABLA");
		movie1.setGenre("Sci-Fi");
		
		Movie movie2 = new Movie();
		movie2.setTitle("The Revenant");
		movie2.setDescription("123123123");
		movie2.setGenre("Adventure");
		

		Actor actor1 = new Actor();
		actor1.setFirstname("Leonardo");
		actor1.setLastname("Di Caprio");
		
		Actor actor2 = new Actor();
		actor2.setFirstname("Tom");
		actor2.setLastname("Hardy");
		
		movie1.getActors().add(actor1);
		
		movie2.getActors().add(actor1);
		movie2.getActors().add(actor2);

		movieRepo.save(movie1);
		movieRepo.save(movie2);

		System.out.println("FIND ALL MOVIES");
		for (Movie m : movieRepo.findAll()) {
			System.out.println(m);
		}
		
		System.out.println("*************");
		System.out.println("FIND MOVIES BY GENRE SCI-FI");
		for (Movie m : movieRepo.findByGenre("Sci-Fi")) {
			System.out.println(m);
		}
		
		System.out.println("*************");
		System.out.println("FIND MOVIES BY TITLE ASC");
		for (Movie m : movieRepo.findByTitleOrderByTitleAsc("Inception")) {
			System.out.println(m);
		}
		
		System.out.println("*************");
		System.out.println("FIND MOVIES WITH DESC TEXT");
		for (Movie m : movieRepo.findAllMoviesContainingDescText("BLA")) {
			System.out.println(m);
		}
		
		
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ch.bitz.SpringData");
		context.getBean(MovieMain.class).call();
		context.close();
	}

}
