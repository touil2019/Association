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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

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


    @RequestMapping(value="/art/{idEvenement}/participer", method=RequestMethod.GET)
    public String participer(@PathVariable("idEvenement") Long idEvenement){

        Reservation reservationArt = new Reservation();

        Membre membre = iAssociationMetier.userConnected();

        Evenement evenement= evenementCulturelRepository.getOne(idEvenement);

        reservationArt.setDateReservation(new Date());
        reservationArt.setMembre(membre);
        reservationArt.setDescription(evenement.getNom());
        reservationArt.setEvenement(evenement);
        reservationRepository.save(reservationArt);

        return "art";
    }
}
