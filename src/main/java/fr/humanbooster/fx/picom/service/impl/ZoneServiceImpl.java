package fr.humanbooster.fx.picom.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.humanbooster.fx.picom.business.Zone;
import fr.humanbooster.fx.picom.dao.ZoneDao;
import fr.humanbooster.fx.picom.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

	// On déclare un objet de type ZoneDao
	private ZoneDao zoneDao;
	
	// Ce constructeur va provoquer l'injection de la dépendance vers zoneDao
	// autrement dit, Spring va instancier un objet de type ZoneDao
	public ZoneServiceImpl(ZoneDao zoneDao) {
		super();
		this.zoneDao = zoneDao;
	}


	@Override
	public Zone ajouterZone(String nom) {
		return zoneDao.save(new Zone(nom));
	}


	@Override
	public Zone recupererZone(Long id) {
		if (zoneDao.findById(id).isPresent()) {
			return zoneDao.findById(id).get();
		}
		else {
			return null;
		}
		//return zoneDao.findById(id).orElse(null);
	}


	@Override
	public List<Zone> recupererZones() {
		return zoneDao.findAll();
	}


	@Override
	public Zone recupererZone(String nom) {
		// la ligne ci-dessous nous évite d'écrire un branchement if else
		return zoneDao.findByNom(nom).orElse(null);
	}


	@Override
	public Page<Zone> recupererZones(Pageable pageable) {
		return zoneDao.findAll(pageable);
	}


	@Override
    public boolean supprimerZone(Long id) {
        Zone zone = recupererZone(id);
        if (zone != null) {
            zoneDao.delete(zone);
            return true;
        }
        return false;
    }

}
