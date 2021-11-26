package fr.humanbooster.fx.picom.business;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class AnnonceHtml extends Annonce{

	@Lob
	private String contenu;
	
	public AnnonceHtml() {
		// TODO Auto-generated constructor stub
	}
	
}
