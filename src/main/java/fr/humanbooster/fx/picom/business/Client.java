package fr.humanbooster.fx.picom.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Client extends Utilisateur {

	@Column(length=15) // Contrainte sur la colonne
	@Size(max=15) // Contrainte métier
	private String numeroDeTelephone;

	@OneToMany(mappedBy="client")
	private List<Annonce> annonces;
	
	@OneToMany(mappedBy="client")
	private List<CarteBancaire> cartesBancaires;
	
	public Client() {
	}
	
	public Client(String nom, String prenom, String email, String motDePasse, String numeroDeTelephone) {
		super(nom, prenom, email, motDePasse);
		this.numeroDeTelephone = numeroDeTelephone;
	}
	
	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}

	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	@Override
	public String toString() {
		return "Client [numeroDeTelephone=" + numeroDeTelephone + ", getId()=" + getId() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getEmail()=" + getEmail() + ", getMotDePasse()=" + getMotDePasse()
				+ "]";
	}
	
	
}
