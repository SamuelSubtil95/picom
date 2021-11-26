package fr.humanbooster.fx.picom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.humanbooster.fx.picom.business.Zone;

public interface ZoneService {

	Zone ajouterZone(String nom);
	
	Zone recupererZone(Long id);
	
	Zone recupererZone(String nom);
	
	List<Zone> recupererZones();

	Page<Zone> recupererZones(Pageable pageable);

	boolean supprimerZone(Long id);
}
