package sae.planning.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sae.planning.pojo.Creneau;

public interface CreneauRepository extends CrudRepository<Creneau, Integer> {
	
	@Query("Select a from Creneau a where a.date =:date")
	public List<Creneau> findByDate(@Param("date") Date date);

}
