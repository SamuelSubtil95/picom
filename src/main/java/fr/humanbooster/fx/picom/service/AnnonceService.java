package fr.humanbooster.fx.picom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.humanbooster.fx.picom.business.Annonce;
import fr.humanbooster.fx.picom.business.AnnonceHtml;
import fr.humanbooster.fx.picom.business.AnnonceImage;
import fr.humanbooster.fx.picom.business.Client;

public interface AnnonceService {

	List<Annonce> recupererAnnonces(Client client);

	AnnonceImage enregistrerAnnonceImage(AnnonceImage annonceImage);

	AnnonceHtml enregistrerAnnonceHtml(AnnonceHtml annonceHtml);

	Annonce recupererAnnonce(Long id);

	Page<Annonce> recupererAnnonces(Pageable pageable, Client client);

	boolean supprimerAnnonce(Long id);
	
	List<Annonce> recupererAnnonces();
}
