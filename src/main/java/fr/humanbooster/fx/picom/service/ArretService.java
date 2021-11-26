package fr.humanbooster.fx.picom.service;

import java.util.List;

import fr.humanbooster.fx.picom.business.Arret;
import fr.humanbooster.fx.picom.business.Zone;

public interface ArretService {

	Arret ajouterArret(String nom, Long idZone);
	
	Arret enregistrerArret(Arret arret);

	List<Arret> recupererArrets();
	
	List<Arret> recupererArrets(Zone zone);
}
