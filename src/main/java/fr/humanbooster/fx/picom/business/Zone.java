package fr.humanbooster.fx.picom.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Zone {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy="zone", fetch=FetchType.EAGER)
	private List<Arret> arrets;
	
	@ManyToMany(mappedBy="zones")
	private List<Annonce> annonces;
	
	public Zone() {
	}

	public Zone(String nom) {
		this.nom = nom;
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
	
	public List<Arret> getArrets() {
		return arrets;
	}

	public void setArrets(List<Arret> arrets) {
		this.arrets = arrets;
	}

	@Override
	public String toString() {
		return "Zone [id=" + id + ", nom=" + nom + "]";
	}
	
}
