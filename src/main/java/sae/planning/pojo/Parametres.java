package sae.planning.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "parametres")
public class Parametres {

	@Id
	@Getter@Setter
	private int id;
	@Getter@Setter
	@NotNull
    private int duree;
	@Getter@Setter
	@NotNull
	@Min(value=1, message="trop petit")
    private int nb_places;
    
    
	@Override
	public String toString() {
		return "Parametres [id=" + id + ", duree=" + duree + ", nb_places=" + nb_places + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public int getNb_places() {
		return nb_places;
	}


	public void setNb_places(int nb_places) {
		this.nb_places = nb_places;
	}
	
	
	
	
	
	
	
}
