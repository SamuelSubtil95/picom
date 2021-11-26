package fr.humanbooster.fx.picom.business;

public class LoginForm {

	private String email;
	private String motDePasse;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse; 
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public LoginForm(String email, String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}
	public LoginForm() {
	}
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", motDePasse=" + motDePasse + "]";
	}
}
