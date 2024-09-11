package sae.planning.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sae.planning.pojo.AssociationCnoUno;
import sae.planning.pojo.Creneau;
import sae.planning.pojo.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, AssociationCnoUno>{

	@Query("Select a from Reservation a where a.user.uno =:uno")
	public List<Reservation> findByUser(@Param("uno") Integer uno);
	
	@Query("Select a from Reservation a where a.user.uno =:uno and a.creneau.cno=:cno")
	public Reservation findByUnoCno(@Param("uno") Integer uno, @Param("cno") Integer cno);
	
	@Query("Select a from Reservation a where a.creneau.heure =:heure and a.creneau.date=:date")
	public List<Reservation> findByDateAndHeure(Date date, Time heure);
	
	@Query("Select a from Reservation a where a.creneau.date=:date")
	public List<Reservation> findByDate(Date date);
	
	@Transactional
	public List<Reservation> deleteByCreneau(Creneau creneau);

}
