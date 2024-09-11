package sae.planning.pojo;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Reservation {

	@EmbeddedId
	@Getter
	@Setter
	private AssociationCnoUno key;

	@Getter
	@Setter
	@ManyToOne
	@MapsId("uno")
	@JoinColumn(name = "uno")
	User user;

	@Getter
	@Setter
	@ManyToOne
	@MapsId("cno")
	@JoinColumn(name = "cno")
	Creneau creneau;

	@Getter
	@Setter
	private boolean annulee;

	@Getter
	@Setter
	private int nb_personnes;

	public Reservation(User user, Creneau creneau, int nb_personnes) {
		super();
		this.key = new AssociationCnoUno(user.getUno(), creneau.getCno());
		this.user = user;
		this.creneau = creneau;
		this.annulee = false;
		this.nb_personnes = nb_personnes;
	}

	public Reservation() {
		super();
	}

	public AssociationCnoUno getKey() {
		return key;
	}

	public void setKey(AssociationCnoUno key) {
		this.key = key;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.user = user;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public boolean isAnnulee() {
		return annulee;
	}

	public void setAnnulee(boolean annulee) {
		this.annulee = annulee;
	}

	public int getNb_personnes() {
		return nb_personnes;
	}

	public void setNb_personnes(int nb_personnes) {
		this.nb_personnes = nb_personnes;
	}

}
