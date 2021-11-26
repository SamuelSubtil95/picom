package fr.humanbooster.fx.picom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Zone;

/**
 * Cette interface hérite de l'interface JpaRepository
 * qui fait partie du projet Spring Data
 * @author fxcote
 *
 */
public interface ZoneDao extends JpaRepository<Zone, Long> {

	// requête par dérivation
	// Optional: nouveauté de Java 8
	Optional<Zone> findByNom(String nom);
}
