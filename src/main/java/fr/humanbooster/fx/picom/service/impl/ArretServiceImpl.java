package fr.humanbooster.fx.picom.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.fx.picom.business.Arret;
import fr.humanbooster.fx.picom.business.Zone;
import fr.humanbooster.fx.picom.dao.ArretDao;
import fr.humanbooster.fx.picom.service.ArretService;
import fr.humanbooster.fx.picom.service.ZoneService;

@Service
public class ArretServiceImpl implements ArretService {

	private ArretDao arretDao;
	private ZoneService zoneService;

	public ArretServiceImpl(ArretDao arretDao, ZoneService zoneService) {
		super();
		this.arretDao = arretDao;
		this.zoneService = zoneService;
	}

	@Override
	public Arret ajouterArret(String nom, Long idZone) {
		Zone zone = zoneService.recupererZone(idZone);
		if (zone==null) {
			return null;
		}
		else {
			return arretDao.save(new Arret(nom, zone));
		}
	}

	@Override
	public List<Arret> recupererArrets() {
		return arretDao.findAll();
	}

	@Override
	public List<Arret> recupererArrets(Zone zone) {
		return arretDao.findByZone(zone);
	}

	@Override
	public Arret enregistrerArret(Arret arret) {
		return arretDao.save(arret);
	}

}
