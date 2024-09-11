package sae.planning.pojo;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "joursTravail")
public class JoursTravailles {

	@Id
	@Getter@Setter
	int jour;
	@Getter@Setter
	LocalTime debutmat;
	@Getter@Setter
	LocalTime finmat;
	@Getter@Setter
	LocalTime debutap;
	@Getter@Setter
	LocalTime finap;
	
	
	@Override
	public String toString() {
		return "JoursTravail [jour=" + jour + ", debutmat=" + debutmat + ", finmat=" + finmat + ", debutap=" + debutap
				+ ", finap=" + finap + "]";
	}


	public int getJour() {
		return jour;
	}


	public void setJour(int jour) {
		this.jour = jour;
	}


	public LocalTime getDebutmat() {
		return debutmat;
	}


	public void setDebutmat(LocalTime debutmat) {
		this.debutmat = debutmat;
	}


	public LocalTime getFinmat() {
		return finmat;
	}


	public void setFinmat(LocalTime finmat) {
		this.finmat = finmat;
	}


	public LocalTime getDebutap() {
		return debutap;
	}


	public void setDebutap(LocalTime debutap) {
		this.debutap = debutap;
	}


	public LocalTime getFinap() {
		return finap;
	}


	public void setFinap(LocalTime finap) {
		this.finap = finap;
	}
	
	
	
	
	
	
}
