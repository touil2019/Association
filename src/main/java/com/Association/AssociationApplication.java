package com.Association;

import com.Association.dao.CommentairesRepository;
import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Commentaires;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;
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
	EvenementCulturelRepository evenementCulturelRepository;

	@Autowired
	CommentairesRepository commentairesRepository;

	@Autowired
	ReservationRepository reservationRepository;

	public static void main(String[] args) {

		SpringApplication.run(AssociationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@PostConstruct
	public void postConstruct(){


		/**
 		* Rôles et Membres de l'Association
		*
		*/
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
		admin.setEnabled(true);
		membreRepository.save(admin);


		/**
		 * Evènement Culturel
		 */
		Evenement evenement = new Evenement("Art","Atelier Art Plastique",new GregorianCalendar(2021, Calendar.FEBRUARY,10).getTime(),10,"Atelier d'art plastique");
		evenementCulturelRepository.save(evenement);

		Evenement evenement1 = new Evenement("Culture","Club De Lecture",new GregorianCalendar(2021, Calendar.FEBRUARY,12).getTime(),10,"Séance de lecture libre et Club de lecture");
		evenementCulturelRepository.save(evenement1);

		Evenement evenement2 = new Evenement("Conference","Vivre Ensemble",new GregorianCalendar(2021, Calendar.FEBRUARY,13).getTime(),10,"Conférence sur la culture du vivre-ensemble");
		evenementCulturelRepository.save(evenement2);

		/**
		 * commentaires des membres
		 */
		Commentaires commentaires = new Commentaires(1L,actif1,"Super ambiance lors du dernier atelier musique et l'initiation au didgeridoo",new GregorianCalendar(2021, Calendar.FEBRUARY,3).getTime());
		commentairesRepository.save(commentaires);

		Commentaires commentaires1 = new Commentaires(2L,actif,"Super ambiance lors du dernier atelier musique et l'initiation au didgeridoo",new GregorianCalendar(2021, Calendar.FEBRUARY,3).getTime());
		commentairesRepository.save(commentaires1);

		/**
		 * réservations évènements
		 */
		Reservation reservation = new Reservation(new GregorianCalendar(2021, Calendar.FEBRUARY,2).getTime(), evenement.getDescription(), actif, evenement);
		reservationRepository.save(reservation);

		Reservation reservation1 = new Reservation(new GregorianCalendar(2021, Calendar.FEBRUARY,12).getTime(), evenement1.getDescription(), actif1, evenement1);
		reservationRepository.save(reservation1);



	}
}
