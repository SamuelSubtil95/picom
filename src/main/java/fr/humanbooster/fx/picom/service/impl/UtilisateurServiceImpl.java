package fr.humanbooster.fx.picom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.humanbooster.fx.picom.business.Administrateur;
import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.business.Utilisateur;
import fr.humanbooster.fx.picom.dao.AdministrateurDao;
import fr.humanbooster.fx.picom.dao.ClientDao;
import fr.humanbooster.fx.picom.dao.UtilisateurDao;
import fr.humanbooster.fx.picom.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private ClientDao clientDao;
	private AdministrateurDao administrateurDao;
	private UtilisateurDao utilisateurDao;
	
	public UtilisateurServiceImpl(ClientDao clientDao, AdministrateurDao administrateurDao,
			UtilisateurDao utilisateurDao) {
		super();
		this.clientDao = clientDao;
		this.administrateurDao = administrateurDao;
		this.utilisateurDao = utilisateurDao;
	}

	@Override
	public Client enregistrerClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public Administrateur enregistrerAdministrateur(Administrateur administrateur) {
		return administrateurDao.save(administrateur);
	}

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findByEmailAndMotDePasse(email, motDePasse);
	}

	@Override
	public Page<Utilisateur> recupererUtilisateurs(Pageable pageable) {
		return utilisateurDao.findAll(pageable);
	}

	@Override
	public Page<Utilisateur> recupererUtilisateurs(Pageable pageable, String filtreNom) {
		return utilisateurDao.findByNomContaining(pageable, filtreNom);
	}

	@Override
	public List<Utilisateur> recupererUtilisateurs() {
		return utilisateurDao.findAll();
	}

	@Override
	public long compterNbUtilisateurs() {
		return utilisateurDao.count();
	}

	@Override
	public Client recupererUserParId(long id) {
		return (Client) utilisateurDao.findById(id).orElse(null);
	}

}
