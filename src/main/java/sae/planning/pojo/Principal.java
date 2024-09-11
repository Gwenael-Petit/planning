package sae.planning.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Principal {

	@Getter@Setter
	private int id;
	@Getter@Setter
	private String login;
	@Getter@Setter
	private String role;
	
	
	
	public Principal(int id, String login, String role) {
		super();
		this.id = id;
		this.login = login;
		this.role = role;
	}



	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
