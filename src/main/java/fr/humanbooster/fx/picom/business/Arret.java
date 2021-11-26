package fr.humanbooster.fx.picom.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Arret {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Merci de renseigner le nom de l''arrêt")
	private String nom;
	
	@JsonIgnore
	@ManyToOne
	@NotNull(message="Merci de préciser la zone")
	private Zone zone;
	
	public Arret() {
	}

	public Arret(String nom, Zone zone) {
		super();
		this.nom = nom;
		this.zone = zone;
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

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	@Override
	public String toString() {
		return "Arret [id=" + id + ", nom=" + nom + ", zone=" + zone + "]";
	}
	
}
