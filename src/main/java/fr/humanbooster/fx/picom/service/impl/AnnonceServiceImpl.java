package fr.humanbooster.fx.picom.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.humanbooster.fx.picom.business.Annonce;
import fr.humanbooster.fx.picom.business.AnnonceHtml;
import fr.humanbooster.fx.picom.business.AnnonceImage;
import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.dao.AnnonceDao;
import fr.humanbooster.fx.picom.dao.AnnonceHtmlDao;
import fr.humanbooster.fx.picom.dao.AnnonceImageDao;
import fr.humanbooster.fx.picom.service.AnnonceService;

@Service
public class AnnonceServiceImpl implements AnnonceService {

	private AnnonceDao annonceDao;
	private AnnonceImageDao annonceImageDao;
	private AnnonceHtmlDao annonceHtmlDao;

	public AnnonceServiceImpl(AnnonceDao annonceDao, AnnonceImageDao annonceImageDao, AnnonceHtmlDao annonceHtmlDao) {
		super();
		this.annonceDao = annonceDao;
		this.annonceImageDao = annonceImageDao;
		this.annonceHtmlDao = annonceHtmlDao;
	}

	@Override
	public List<Annonce> recupererAnnonces(Client client) {
		return annonceDao.findByClient(client);
	}

	@Override
	public AnnonceImage enregistrerAnnonceImage(AnnonceImage annonceImage) {
		return annonceImageDao.save(annonceImage);
	}

	@Override
	public AnnonceHtml enregistrerAnnonceHtml(AnnonceHtml annonceHtml) {
		return annonceHtmlDao.save(annonceHtml);
	}

	@Override
	public Annonce recupererAnnonce(Long id) {
		return annonceDao.findById(id).orElse(null);
	}

	@Override
	public Page<Annonce> recupererAnnonces(Pageable pageable, Client client) {
		return annonceDao.findByClient(pageable, client);
	}

	@Override
	public boolean supprimerAnnonce(Long id) {
		Annonce annonce = recupererAnnonce(id);
		if (annonce!=null) {
			annonceDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Annonce> recupererAnnonces() {
		return annonceDao.findAll();
	}

}
