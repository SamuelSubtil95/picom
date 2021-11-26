package fr.humanbooster.fx.picom.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Annonce;
import fr.humanbooster.fx.picom.business.Client;

public interface AnnonceDao extends JpaRepository<Annonce, Long> {

	// Requête par dérivation
	List<Annonce> findByClient(Client client);

	List<Annonce> findByClientAndDateDebutBetween(Client client, Date dateDebut, Date dateFin);

	Page<Annonce> findByClient(Pageable pageable, Client client);
}
