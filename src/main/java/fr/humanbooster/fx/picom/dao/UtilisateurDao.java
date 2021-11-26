package fr.humanbooster.fx.picom.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Utilisateur;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);

	Page<Utilisateur> findByNomContaining(Pageable pageable, String nom);

}
