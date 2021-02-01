package com.Association;

import com.Association.dao.CommentairesRepository;
import com.Association.dao.EvenementCulturellesRepository;
import com.Association.dao.MembreRepository;
import com.Association.entities.Commentaires;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.security.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class AssociationApplication implements CommandLineRunner {

	@Autowired
	MembreRepository membreRepository;

	@Autowired
	EvenementCulturellesRepository evenementCulturellesRepository;

	@Autowired
	CommentairesRepository commentairesRepository;

	public static void main(String[] args) {

		SpringApplication.run(AssociationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@PostConstruct
	public void postConstruct(){

		Membre actif = new Membre("membre","membre","membre@gmail.com","membre","membre",34,"18 Rue Du Colonnel FABIEN","1 étage porte droite","75011","PARIS");
		Set<RoleEnum> actifRole = new HashSet<>();
		actifRole.add(RoleEnum.ROLE_ACTIF);
		actif.setRoles(actifRole);
		membreRepository.save(actif);

		Membre actif1 = new Membre("membre1","membre1","membre1@gmail.com","membre1","membre1",29,"18 rue Jean Mermoz","2 étage","75020","PARIS");
		Set<RoleEnum> actif1Role = new HashSet<>();
		actif1Role.add(RoleEnum.ROLE_ACTIF);
		actif1.setRoles(actif1Role);
		membreRepository.save(actif1);


		Membre bienfaiteur = new Membre("bienfaiteur","bienfaiteur","bienfaiteur@gmail.com","bienfaiteur","bienfaiteur",25,"10 Place Colonnel Fabien",null,"75012","PARIS");
		Set<RoleEnum> bienfaiteurRole = new HashSet<>();
		bienfaiteurRole.add(RoleEnum.ROLE_BIENFAITEUR);
		bienfaiteur.setRoles(bienfaiteurRole);
		membreRepository.save(bienfaiteur);


		Membre honoraire = new Membre("honoraire","honoraire","honoraire@gmail.com","honoraire","honoraire",25,"10 rue Jean Pierre Timbaud",null,"75012","PARIS");
		Set<RoleEnum> honoraireRole= new HashSet<>();
		honoraireRole.add(RoleEnum.ROLE_HONORAIRE);
		honoraire.setRoles(honoraireRole);
		membreRepository.save(honoraire);

		Membre admin = new Membre("admin","admin","admin@gmail.com","admin","admin",43,"18 Rue Du Philibert HOFFMANN",null,"75012","PARIS");
		Set<RoleEnum> adminRole = new HashSet<>();
		adminRole.add(RoleEnum.ROLE_ADMIN);
		adminRole.add(RoleEnum.ROLE_ACTIF);
		adminRole.add(RoleEnum.ROLE_BIENFAITEUR);
		adminRole.add(RoleEnum.ROLE_HONORAIRE);
		admin.setRoles(adminRole);
		membreRepository.save(admin);


		Evenement evenement = new Evenement("Art",new GregorianCalendar(2021, Calendar.FEBRUARY,10).getTime(),10,"Atelier d'art plastique");
		evenementCulturellesRepository.save(evenement);

		Evenement evenement1 = new Evenement("Musique",new GregorianCalendar(2021, Calendar.FEBRUARY,12).getTime(),10,"Chorale & Orchestre de l'association afin de réaliser un concert");
		evenementCulturellesRepository.save(evenement1);

		Evenement evenement2 = new Evenement("Conférence",new GregorianCalendar(2021, Calendar.FEBRUARY,13).getTime(),10,"Conférence sur la culture du vivre-ensemble");
		evenementCulturellesRepository.save(evenement2);

		Commentaires commentaires = new Commentaires(1L,actif1,"Super ambiance lors du dernier atelier musique et l'initiation au didgeridoo",new GregorianCalendar(2021, Calendar.FEBRUARY,3).getTime());
		commentairesRepository.save(commentaires);

		Commentaires commentaires1 = new Commentaires(2L,actif,"Super ambiance lors du dernier atelier musique et l'initiation au didgeridoo",new GregorianCalendar(2021, Calendar.FEBRUARY,3).getTime());
		commentairesRepository.save(commentaires1);
	}
}
