package fr.humanbooster.fx.picom.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Merci de préciser votre nom")
	private String nom;

	@NotBlank(message = "Merci de renseigner votre prénom")
	private String prenom;

	// @NotNull(message="L''email est inconnu")
	@Email(message = "Merci de saisir un email au bon format")
	@NotBlank(message = "Merci de renseigner votre email")
	private String email;

	@NotBlank(message = "Merci de renseigner votre mot de passe")
	@Size(min = 8, message = "Merci de saisir un mot de passe contenant au moins 8 caractères")
	private String motDePasse;

	public Utilisateur() {
	}

	public Utilisateur(String nom, String prenom, String email, String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
