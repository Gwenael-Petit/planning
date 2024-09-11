package sae.planning.pojo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.ToString;

@Embeddable
@ToString
public class AssociationCnoUno implements Serializable {

	 @Column(name="uno")
	 private int uno;
	 
	 @Column(name="cno")
	 private int cno;
	

	public AssociationCnoUno(int uno2, int cno2) {
			this.cno = cno2;
			this.uno = uno2;
		}

	public AssociationCnoUno() {
		super();
	}
	 
	 
	
}
