package sae.planning.pojo;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "creneau")
public class Creneau {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter@Setter
	private int cno;
	@Getter@Setter
	private Date date;
	@Getter@Setter
	private Time heure;
	@Getter@Setter
	@Min(value=0, message="trop petit")
	private int places_restantes;
	@Getter@Setter
	private int duree;
	
	@OneToMany(mappedBy = "creneau")
    Set<Reservation> reservations;
	

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time heure) {
		this.heure = heure;
	}

	public int getPlaces_restantes() {
		return places_restantes;
	}

	public void setPlaces_restantes(int places_restantes) {
		this.places_restantes = places_restantes;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "Creneau [date=" + date + "heure=" + heure +", placeRestantes=" + places_restantes + ", duree=" + duree + "]";
	}

	public Creneau(Date date, Time heure, int places_restantes, int duree) {
		super();
		this.date = date;
		this.heure = heure;
		this.places_restantes = places_restantes;
		this.duree = duree;
	}

	public Creneau() {
		super();
	}
	
}
