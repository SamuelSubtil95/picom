package fr.humanbooster.fx.picom.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TrancheHoraire {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int debut;
	
	@ManyToMany(mappedBy="tranchesHoraires")
	private List<Annonce> annonces;
	
	public TrancheHoraire() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	@Override
	public String toString() {
		return "TrancheHoraire [id=" + id + ", debut=" + debut + "]";
	}
	
}