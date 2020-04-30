package ch.bitz.SpringData.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByFirstname(String firstname);

	List<Person> findByFirstnameAndLastname(String firstname, String lastname);
	
	Optional<Person> findByLastname(String lastname);

//	@Query("select p from Person where p.firstname = ?1 and p.lastname = ?2")
//	List<Person> myQuery(String f, String l);

	@Query("select p from Person p where p.firstname = :f and p.lastname = :l")
	List<Person> myQuery2(@Param("f") String f, @Param("l") String l);

	@Query(value = "select count(*) from person", nativeQuery = true)
	int myNativeQuery();

}
