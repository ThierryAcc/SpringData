package ch.bitz.SpringData;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import ch.bitz.SpringData.data.Movie;
import ch.bitz.SpringData.data.Person;

@Component
public class Main {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void test() {
		
		Person person = new Person();
		person.setFirstname("Test3");
		person.setLastname("TestLast3");
		person.setBirth(LocalDate.now());
		entityManager.persist(person);
		
		Movie movie = new Movie();
		movie.setTitle("Inception5");
		movie.setDescription("Dank5 modernster Technologie ist es in naher Zukunft möglich, in Träume und somit in das Unterbewusstsein von Menschen einzusteigen.");
		movie.setGenre("Sci-Fi");
		entityManager.persist(movie);
		
		System.out.println(person);
		System.out.println(movie);
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ch.bitz.SpringData");
		context.getBean(Main.class).test();
		context.close();
	}
}

