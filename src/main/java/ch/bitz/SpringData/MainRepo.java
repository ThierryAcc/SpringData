package ch.bitz.SpringData;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import ch.bitz.SpringData.data.Movie;
import ch.bitz.SpringData.data.MovieRepository;
import ch.bitz.SpringData.data.Person;
import ch.bitz.SpringData.data.PersonRepository;

@Component
public class MainRepo {

	@Autowired
	PersonRepository personRepo;

	@Autowired
	MovieRepository movieRepo;

	@Transactional
	public void test() {

		Person person = new Person();
		person.setFirstname("Test3");
		person.setLastname("TestLast3");
		person.setBirth(LocalDate.now());
		personRepo.save(person);

		Movie movie = new Movie();
		movie.setTitle("Inception2");
		movie.setDescription(
				"Dank5 modernster Technologie ist es in naher Zukunft möglich, in Träume und somit in das Unterbewusstsein von Menschen einzusteigen.");
		movie.setGenre("Sci-Fi");
		movieRepo.save(movie);

		for (Person p : personRepo.findByFirstname("Test3")) {
			System.out.println(p);
		}
		
		System.out.println("*****");
		
		for (Person p : personRepo.findByFirstnameAndLastname("Test1", "TestLast1")) {
			System.out.println(p);
		}
		
		System.out.println("*****");
		
		for (Person p : personRepo.myQuery2("Test3", "TestLast3")) {
			System.out.println(p);
		}
		
		System.out.println("*****");
		
		System.out.println(personRepo.myNativeQuery());
		
		System.out.println("*****");
		
		System.out.println(personRepo.findByLastname("hallllooooo"));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ch.bitz.SpringData");
		context.getBean(MainRepo.class).test();
		context.close();
	}
}
