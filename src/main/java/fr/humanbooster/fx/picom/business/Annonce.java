package fr.humanbooster.fx.picom.business;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance
public abstract class Annonce {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
	private List<TrancheHoraire> tranchesHoraires;
	
	@ManyToMany
	@NotEmpty(message="Une annonce doit être associée à au moins une zone")
	private List<Zone> zones;
	
	protected Date dateCreation;

	// Le format attendu par Spring, ce format est celui utilisé par le navigateur internet
	// pour transmettre une date saisie sur un formulaire HTML
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Merci de préciser la date à laquelle la diffusion de l'annonce doit débuter")
	private Date dateDebut;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Merci de préciser la date à laquelle la diffusion de l'annonce doit se terminer")
	private Date dateFin;

	@ManyToOne
	@NotNull(message="Une annonce doit être associée à un client")
	protected Client client;
	
	public Annonce(Date dateCreation,
			@NotNull(message = "Une annonce doit être associée à un client") Client client) {
		super();
		this.dateCreation = dateCreation;
		this.client = client;
	}

	public Annonce() {
		dateCreation = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TrancheHoraire> getTranchesHoraires() {
		return tranchesHoraires;
	}

	public void setTranchesHoraires(List<TrancheHoraire> tranchesHoraires) {
		this.tranchesHoraires = tranchesHoraires;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Annonce [id=" + id + ", tranchesHoraires=" + tranchesHoraires + ", zones=" + zones + ", dateCreation="
				+ dateCreation + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", client=" + client + "]";
	}

}