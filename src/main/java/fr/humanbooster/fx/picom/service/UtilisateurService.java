package fr.humanbooster.fx.picom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.humanbooster.fx.picom.business.Administrateur;
import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.business.Utilisateur;

public interface UtilisateurService {

	Client enregistrerClient(Client client);
	
	Administrateur enregistrerAdministrateur(Administrateur administrateur);

	Utilisateur recupererUtilisateur(String email, String motDePasse);

	Page<Utilisateur> recupererUtilisateurs(Pageable pageable);
	Page<Utilisateur> recupererUtilisateurs(Pageable pageable, String filtreNom);

	List<Utilisateur> recupererUtilisateurs();

	long compterNbUtilisateurs();

	Client recupererUserParId(long id);

}
