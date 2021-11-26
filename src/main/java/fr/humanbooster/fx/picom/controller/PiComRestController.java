package fr.humanbooster.fx.picom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.humanbooster.fx.picom.business.Annonce;
import fr.humanbooster.fx.picom.business.Arret;
import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.business.LoginForm;
import fr.humanbooster.fx.picom.business.Utilisateur;
import fr.humanbooster.fx.picom.business.Zone;
import fr.humanbooster.fx.picom.service.AnnonceService;
import fr.humanbooster.fx.picom.service.ArretService;
import fr.humanbooster.fx.picom.service.UtilisateurService;
import fr.humanbooster.fx.picom.service.ZoneService;

@RestController
public class PiComRestController {

	private ZoneService zoneService;
	private ArretService arretService;
	private UtilisateurService utilisateurService;
	private AnnonceService annonceService;
	
	public PiComRestController(ZoneService zoneService, ArretService arretService, 
			UtilisateurService utilisateurService, AnnonceService annonceService) {
		super();
		this.zoneService = zoneService;
		this.utilisateurService = utilisateurService;
		this.arretService = arretService;
		this.annonceService = annonceService;
	}

	@GetMapping("ws/zones")
	public List<Zone> zonesGet() {
		return zoneService.recupererZones();
	}
	
	@GetMapping("ws/arrets")
	public List<Arret> arretsGet(){
		return arretService.recupererArrets();
	} 
	
	@PostMapping("ws/zones/{nom}")
	public Zone zonesPost(@PathVariable String nom) {
		Zone zone = zoneService.ajouterZone(nom);
		return zone;
	}
	
	@DeleteMapping("ws/zones/{id}")
	public boolean zonesDelete(@PathVariable Long id) {
		return zoneService.supprimerZone(id);
	}
	
    @PostMapping("ws/inscription")
    public Client enregistrerClient(@RequestBody Client client) {
        return utilisateurService.enregistrerClient(client);
    } 
        
    // todo : à tester
    @PostMapping("/ws/thelogin")
    public Utilisateur postTheLogin(@RequestBody LoginForm loginForm) {
    	return utilisateurService.recupererUtilisateur(loginForm.getEmail(), loginForm.getMotDePasse());
    }
    
    // todo : à tester
    // Object [id=1] was not of the specified subclass [fr.humanbooster.fx.picom.business.Annonce] : Discriminator: 
    @GetMapping("ws/annonces/user/{id}")
    public List<Annonce> getAllArretsFromUser(@PathVariable long id) {
    	return annonceService.recupererAnnonces(utilisateurService.recupererUserParId(id));
    }
    
    // todo : à tester
    @PostMapping("ws/addarret/{nom}/{idZone}")
    public Arret ajouterArret(@PathVariable String nom,@PathVariable Long idZone) {
        return arretService.ajouterArret(nom, idZone);
    }
    
}
