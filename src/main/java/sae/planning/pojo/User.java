package sae.planning.pojo;

import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "utilisateur")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uno;
	@Email
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String pwd;
	@NotNull
	@NotBlank
	private String nom;
	@NotNull
	@NotBlank
	private String prenom;
	private String role;
	@NotNull
	@NotBlank
	private String telephone;
	@NotNull
	@NotBlank
	private String date_naiss;
	private String photo;
	
	
	@OneToMany(mappedBy = "user")
    Set<Reservation> reservations;
	
	public User() {
		
	}
	
	public User(String email, String pwd, String nom, String prenom, String role, String telephone, String date_naiss,
			String photo) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.telephone = telephone;
		this.date_naiss = date_naiss;
		this.photo = photo;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDate_naiss() {
		return date_naiss;
	}

	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [uno=" + uno + ", email=" + email + ", pwd=" + pwd + ", nom=" + nom + ", prenom=" + prenom
				+ ", role=" + role + ", telephone=" + telephone + ", date_naiss=" + date_naiss + ", photo=" + photo
				+ "]";
	}

	
	
	
	
	
	

}
