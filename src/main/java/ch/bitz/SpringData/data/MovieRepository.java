package ch.bitz.SpringData.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByGenre(String genre);
	
	//findByAgeOrderByLastnameDesc
	List<Movie> findByTitleOrderByTitleAsc(String title);

	@Query("select m from Movie m where m.description like %:f%")
	List<Movie> findAllMoviesContainingDescText(@Param("f") String f);
	
	@Query("select m from Movie m, Actor a where m.id = ")
	List<Movie> findAllMoviesContainingDescText2(@Param("f") String f);
	
}
