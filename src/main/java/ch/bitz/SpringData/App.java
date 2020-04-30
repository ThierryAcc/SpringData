package ch.bitz.SpringData;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ch.bitz.SpringData.data.Person;

public class App 
{

	/**
	 *  ist ein feld in meiner klasse mit dieser Annotation konfiguriert
	 *  und baut dann den  
	 */
	
    @PersistenceContext
    EntityManager entityManager;
    
    /**
     * vor dem betreten der Methode markiert er die Transaktion
     * brauchen keine begin() und commit()
     */
    @Transactional
    public void test() {
    	Person person = new Person();
    	person.setFirstname("Test");
    	person.setLastname("Bitz");
    	person.setBirth(LocalDate.now());
    	
    	entityManager.persist(person);
    }
	
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ch.bitz.SpringData");
        context.getBean(App.class).test();
        context.close();
        
    }
}
