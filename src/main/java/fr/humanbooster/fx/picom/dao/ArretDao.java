package fr.humanbooster.fx.picom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Arret;
import fr.humanbooster.fx.picom.business.Zone;

public interface ArretDao extends JpaRepository<Arret, Long> {

	List<Arret> findByZone(Zone zone);

	List<Arret> findByNomContaining(String filtre);
}
