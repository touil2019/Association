package com.Association.web;

import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(ReservationController.class);

    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private EvenementCulturelRepository evenementCulturelRepository;

    /**
     * Méthode pour enregistrer la participation d un membre à un événement
     * @param model
     * @param id identifiant de l'événement
     * @return retourne la page d'accueil
     */
    @RequestMapping(value="/evenement/{id}/participer", method=RequestMethod.GET)
    public String participer(Model model, @PathVariable("id") Long id){

        Reservation reservation = new Reservation();

        Membre membre = iAssociationMetier.userConnected();

        Optional<Evenement> e= evenementCulturelRepository.findById(id);

        Evenement evenement= null;


        if(e.isPresent()){

            evenement=e.get();
            Boolean participe=iAssociationMetier.membreParticipeDeja(evenement.getId(), membre.getId());

            if(participe==false && evenement.getNombreParticipant()<= evenement.getNombreParticipantMax()){

                reservation.setDateReservation(new Date());
                reservation.setMembre(membre);
                reservation.setEvenement(evenement);
                reservationRepository.save(reservation);

                evenement.setNombreParticipant(evenement.getNombreParticipant()+1);
                evenementCulturelRepository.save(evenement);

                List<Reservation> reservations = iAssociationMetier.reservationsParMembre(membre.getId());

                model.addAttribute("reservations", reservations);
                logger.info("Un membre vient de valider sa participation à l'événement");
                return "profil";
            } else return "redirect:/home";

        }else return "redirect:/home";
    }

    /**
     * Méthode pour annuler la participation d un membre
     * @param id identifiant de la reservation
     * @return page profil
     */
    @GetMapping(value = "/reservation/{id}/annuler")
    public String annulerReservation(@PathVariable("id") Long id){

        Membre membre = iAssociationMetier.userConnected();

        Reservation reservation = reservationRepository.findById(id).get();

        if(reservation.getMembre().getId()== membre.getId()){
            reservationRepository.deleteById(id);
        }
        logger.info("Un membre vient d'annuler sa participation à l'événement");
        return "redirect:/profil";
    }
}
