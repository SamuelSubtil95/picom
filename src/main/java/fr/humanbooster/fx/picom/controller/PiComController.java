package fr.humanbooster.fx.picom.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.fx.picom.business.Administrateur;
import fr.humanbooster.fx.picom.business.Annonce;
import fr.humanbooster.fx.picom.business.AnnonceImage;
import fr.humanbooster.fx.picom.business.Arret;
import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.business.Utilisateur;
import fr.humanbooster.fx.picom.business.Zone;
import fr.humanbooster.fx.picom.service.AnnonceService;
import fr.humanbooster.fx.picom.service.ArretService;
import fr.humanbooster.fx.picom.service.UtilisateurService;
import fr.humanbooster.fx.picom.service.ZoneService;

@Controller
@SessionAttributes("annonceImage")
public class PiComController {

	private ZoneService zoneService;
	private ArretService arretService;
	private UtilisateurService utilisateurService;
	private AnnonceService annonceService;
	private HttpSession httpSession;

	// En entreprise, on aurait private static final String DOSSIER_IMAGES = "/mnt/san/images/";
	private static final String DOSSIER_IMAGES = "src/main/webapp/images/";

	// Constructeur avec tous les objets que Spring doit injecter
	public PiComController(ZoneService zoneService, ArretService arretService, UtilisateurService utilisateurService,
			AnnonceService annonceService, HttpSession httpSession) {
		super();
		this.zoneService = zoneService;
		this.arretService = arretService;
		this.utilisateurService = utilisateurService;
		this.annonceService = annonceService;
		this.httpSession = httpSession;
	}

	/**
	 * Cette méthode sera invoquée lorsqu'un internaute se rend sur l'url
	 * localhost:8080/accueil ou localhost:8080/index ou localhost:8080
	 * 
	 * @return un objet de type ModelAndView (== une Danette Pop)
	 * Le modèle correspond à tous les objets métier qui sont envoyés à la vue
	 * La vue (dans notre projet) sera une et une seule JSP
	 */
	@RequestMapping({ "/", "index", "accueil" })
	public ModelAndView accueil() {
		System.out.println(new Date() + " requete en GET sur l'url / index ou accueil");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index"); // redirige vers la JSP index.jsp
		return mav;
	}

	/**
	 * Cette méthode traite une requête HTTP dont la méthode est GET et l'url
	 * utilisateurs
	 * 
	 * @return
	 */
	@GetMapping("utilisateurs")
	public ModelAndView utilisateursGet(@PageableDefault(size = 10, sort = "nom") Pageable pageable,
			@RequestParam(name = "FILTRE_NOM", required = false) String filtreNom) {
		System.out.println(new Date() + " requete en GET sur l'url utilisateurs");
		ModelAndView mav = new ModelAndView();

		System.out.println(pageable.getSort());
		// pageable.getSort().iterator().next().getProperty();
		mav.setViewName("utilisateurs");

		if (filtreNom != null) {
			mav.getModel().put("pageDUtilisateurs", utilisateurService.recupererUtilisateurs(pageable, filtreNom));
			mav.addObject("filtreNom", filtreNom);
		} else {
			mav.getModel().put("pageDUtilisateurs", utilisateurService.recupererUtilisateurs(pageable));
		}
		return mav;
	}

	/**
	 * Cette méthode traite une requête HTTP dont la méthode est GET et l'url zones
	 * 
	 * @return
	 */
	@GetMapping("zones")
	public ModelAndView zonesGet(@PageableDefault(size = 10) Pageable pageable) {
		System.out.println(new Date() + " requete en GET sur l'url zones");
		ModelAndView mav = new ModelAndView();

		mav.setViewName("zones");
		mav.getModel().put("pageDeZones", zoneService.recupererZones(pageable));
		return mav;
	}

	/**
	 * Cette méthode traite une requête HTTP dont la méthode est GET et l'url zone
	 * 
	 * Avant on utilisait @WebServlet
	 * 
	 * @return
	 */
	@GetMapping({"zone", "toto"})
	public ModelAndView zoneGet() {
		System.out.println(new Date() + " requete en GET sur l'url zone");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("zone");
		// Le return provoque le dispatch
		return mav;
	}

	/**
	 * Cette méthode traite une requête HTTP dont la méthode est POST et l'url zone
	 * 
	 * @return
	 */
	@PostMapping("zone")
	public ModelAndView zonePost(@RequestParam("NOM") String nom) {
		// On invoque la méthode ajouterZone de ZoneService
		zoneService.ajouterZone(nom);

		// successeur de request.getRequestDispatcher
		// return accueil();

		// successeur de response.sendRedirect()
		return new ModelAndView("redirect:accueil");
	}

	@GetMapping("inscription")
	public ModelAndView inscriptionGet() {
		ModelAndView mav = new ModelAndView("inscription");
		// On ajoute dans le mav un nouvel objet de type Client
		mav.addObject("client", new Client());
		return mav;
	}

	@PostMapping("inscription")
	public ModelAndView inscriptionPost(@Valid @ModelAttribute Client client, BindingResult result) {
		// On demande à Spring s'il a trouvé des erreurs en validant l'objet métier
		if (result.hasErrors()) {
			ModelAndView mav = inscriptionGet();
			// On ne veut pas que le client resaisisse toutes les informations déjà saisies
			mav.addObject("client", client);
			return mav;
		} else {
			utilisateurService.enregistrerClient(client);
			ModelAndView mav = accueil();
			mav.addObject("notification", "Client inscrit");
			return mav;
		}
	}

	@PostMapping("connexion")
	public ModelAndView connexionPost(@RequestParam("EMAIL") String email,
			@RequestParam("MOT_DE_PASSE") String motDePasse) {
		System.out.println(new Date() + " un internaute souhaite se connecter avec l'email " + email);

		// Spring va renvoyer un objet de type Client ou Administrateur
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);
		
		if (utilisateur == null) {
			ModelAndView mav = accueil();
			mav.addObject("notification", "Email et/ou mot de passe incorrect");
			return mav;
		} else {
			System.out.println("Bienvenue à " + utilisateur);
			httpSession.setAttribute("utilisateur", utilisateur);
			// on teste si l'objet utilisateur est une instance de la classe Administrateur
			// if (utilisateur.getClass().getSimpleName().equals("Administrateur"))
			if (utilisateur instanceof Administrateur) {
				return new ModelAndView("redirect:administration");
			} else {
				return new ModelAndView("redirect:annonces");
			}
		}
	}

	@GetMapping("deconnexion")
	public ModelAndView deconnexion() {
		httpSession.invalidate();
		return new ModelAndView("redirect:index");
	}

	@GetMapping("annonces")
	public ModelAndView annoncesGet(@PageableDefault(size=4, sort="dateCreation") Pageable pageable) {
		// On récupère le client en session HTTP
		Client client = (Client) httpSession.getAttribute("utilisateur");
		if (client == null) {
			System.out.println(new Date() + " : Il n'y a pas de client en session");
			return new ModelAndView("redirect:accueil");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageDAnnonces", annonceService.recupererAnnonces(pageable, client));
		mav.setViewName("annonces");
		return mav;
	}

//	@GetMapping("annonceImage")
//	public ModelAndView annonceImageGet(@RequestParam(name="ID", required=false) Long id) {
//		Client client = (Client) httpSession.getAttribute("utilisateur");
//		if (client == null) {
//			System.out.println(new Date() + " : Il n'y a pas de client en session");
//			return new ModelAndView("redirect:accueil");
//		}
//
//		ModelAndView mav = new ModelAndView("annonceImage");
//		
//		AnnonceImage annonceImage = null;
//		if (id==null) {
//			annonceImage = new AnnonceImage();
//			// On associe la nouvelle annonce au client présent dans la session HTTP
//			annonceImage.setClient(client);
//		}
//		else {
//			annonceImage = (AnnonceImage) annonceService.recupererAnnonce(id);
//		}
//		
//		mav.addObject("annonceImage", annonceImage);
//
//		// On place dans l'objet mav la liste des zones car elle est nécessaire à la liste
//		// déroulante des zones
//		mav.addObject("zones", zoneService.recupererZones());
//
//		return mav;
//	}

//	@PostMapping("annonceImage")
//	public ModelAndView annonceImagePost(@Valid @ModelAttribute AnnonceImage annonceImage, BindingResult result) {
//		Client client = (Client) httpSession.getAttribute("utilisateur");
//		if (client == null) {
//			System.out.println(new Date() + " : Il n'y a pas de client en session");
//			return new ModelAndView("redirect:accueil");
//		}
//
//		if (result.hasErrors()) {
//			ModelAndView mav = annonceImageGet(annonceImage.getId());
//			mav.addObject("annonceImage", annonceImage);
//			return mav;
//		} else {
//			annonceService.enregistrerAnnonceImage(annonceImage);
//			return new ModelAndView("redirect:annonces");
//		}
//
//	}

	/**
	 * Cette méthode traite une requête HTTP dont la méthode est GET
	 * (elle sera par exemple invoquée lorsque le client clique sur le lien "Téléverser" présent
	 * sur la page listant ses annonces
	 * 
	 * @param id de l'annonce pour laquelle le client souhaite téléverser une image
	 * @return
	 */
    @GetMapping("/televersementImage")
    public ModelAndView televersementImageGet(@RequestParam("ID") Long id) {
        
    	Client client = (Client) httpSession.getAttribute("utilisateur");
		if (client == null) {
			System.out.println(new Date() + " : Il n'y a pas de client en session");
			return new ModelAndView("redirect:accueil");
		}
		
		Annonce annonce = annonceService.recupererAnnonce(id);
    	
		// On teste si l'annonce n'est pas nulle et qu'elle appartient bien au client en session
		if (annonce!=null && annonce.getClient().getId().equals(client.getId())) {
			// On déclare un objet de type ModelAndView dont la vue est televersementImage.jsp
			ModelAndView mav = new ModelAndView("televersementImage");
			mav.addObject("annonce", annonce);
			return mav;
		}
		else {
			return new ModelAndView("redirect:annonces");
		}
    }

    /**
     * Cette méthode traite une requête HTTP de type POST
     * Cette requête est envoyée par le client HTTP (aka navigateur internet)
     * lorsque le client clique sur le bouton Envoyer du formulaire HTML de téléversement d'image
     * 
     * @param id de l'annonce concernée
     * @param multipartFile flux binaire isolé par Spring
     * @return
     * @throws IOException
     */
    @PostMapping("televersementImage")
    public ModelAndView televersementImagePost(@RequestParam("ID") Long id, @RequestParam("FICHIER") MultipartFile multipartFile) throws IOException {

    	Client client = (Client) httpSession.getAttribute("utilisateur");
		if (client == null) {
			System.out.println(new Date() + " : Il n'y a pas de client en session");
			return new ModelAndView("redirect:accueil");
		}
		
		Annonce annonce = annonceService.recupererAnnonce(id);

		if (annonce!=null && annonce.getClient().getId().equals(client.getId())) {
			System.out.println(new Date() + " : téléversement de l'image pour l'annonce " + id);        
			enregisterFichier(String.valueOf(id), multipartFile);
		}
		return new ModelAndView("redirect:annonces");
    }

    @GetMapping("suppressionAnnonce")
    public ModelAndView suppressionAnnonceGet(@RequestParam("ID") Long id) {

    	Client client = (Client) httpSession.getAttribute("utilisateur");
		if (client == null) {
			System.out.println(new Date() + " : Il n'y a pas de client en session");
			return new ModelAndView("redirect:accueil");
		}

    	Annonce annonce = annonceService.recupererAnnonce(id);
    	
		if (annonce!=null && annonce.getClient().getId().equals(client.getId())) {
			System.out.println(new Date() + " : suppression de l'annonce " + id);
			annonceService.supprimerAnnonce(id);
		}
		return new ModelAndView("redirect:annonces");
    	
    }
    
	@GetMapping("administration")
	public ModelAndView administrationGet() {
		
		if (httpSession.getAttribute("utilisateur") == null) {
			System.out.println(new Date() + " : Il n'y a pas d'utilisateur en session");
			return new ModelAndView("redirect:accueil");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("administration");
		return mav;
	}

	@GetMapping("arretAvecModelAttribute")
	public ModelAndView arretAvecModelAttributeGet() {
		System.out.println(new Date() + " requete en GET sur l'url arretAvecModelAttribute");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("arretAvecModelAttribute");
		mav.addObject("arret", new Arret());
		mav.addObject("zones", zoneService.recupererZones());
		return mav;
	}

	@PostMapping("arretAvecModelAttribute")
	public ModelAndView arretAvecModelAttributePost(@Valid @ModelAttribute Arret arret, BindingResult result) {
		// On demande à Spring s'il a trouvé des erreurs en validant l'objet métier
		if (result.hasErrors()) {
			ModelAndView mav = arretAvecModelAttributeGet();
			// On ne veut pas que l'administrateur resaisisse toutes les informations déjà
			// saisies
			mav.addObject("arret", arret);
			return mav;
		} else {
			arretService.enregistrerArret(arret);
			return new ModelAndView("redirect:accueil");
		}
	}

	private static void enregisterFichier(String nom, MultipartFile multipartFile) throws IOException {
		Path chemin = Paths.get(DOSSIER_IMAGES);

		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path cheminFichier = chemin.resolve(nom);
			Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Erreur d'écriture : " + nom, ioe);
		}
	}

	// @Scheduled(cron="* * * * * *")
	public void ajouterClient() {
		System.out.println("Ajout client");
	}

	@PostConstruct // Spring invoquera cette méthode dès que le bean a été injécté
	private void init() {
		if (zoneService.recupererZones().isEmpty()) {
			for (int i = 1; i <= 100; i++) {
				zoneService.ajouterZone("Zone " + i);
			}
		}

		if (arretService.recupererArrets().isEmpty()) {
			Arret arret = arretService.ajouterArret("Gailleton", zoneService.recupererZone("Zone 1").getId());
			arret.setNom("Place Gailleton");
			arretService.enregistrerArret(arret);
		}

		Zone zone = zoneService.recupererZone("Lyon 2");
		System.out.println(arretService.recupererArrets(zone));
		
		Client client = new Client();
		
		if (utilisateurService.recupererUtilisateurs().size() == 0) {
			client = utilisateurService.enregistrerClient(new Client("nom", "prenom", "email@email.com", "motdepasse", "telephone"));
		}
		

		// on ajoute 100 clients et un admin si la table utilisateur est vide
		if (utilisateurService.compterNbUtilisateurs() == 0) {
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
				utilisateurService.enregistrerClient(new Client("nom" + random.nextInt(500),
						"prenom" + random.nextInt(500), "email" + i + "@hb.com", "motDePasse" + i, "telephone" + i));
			}
			utilisateurService
					.enregistrerAdministrateur(new Administrateur("nom", "prenom", "admin1@hb.com", "12345678"));
		}

	}
}
