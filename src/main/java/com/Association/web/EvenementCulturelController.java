package com.Association.web;

import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Evenement;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EvenementCulturelController {

    private static final Logger logger = LogManager.getLogger(EvenementCulturelController.class);

    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private EvenementCulturelRepository evenementCulturelRepository;


    @GetMapping(value = "/art/{id}")
    public Evenement myEvent(@PathVariable("id")Long id)
    {
        return evenementCulturelRepository.findById(id).get() ; }

    @GetMapping(value = "/art")
    public String eventArt(Model model){

        List<Evenement> evenements= evenementCulturelRepository.listeEvenementParTheme("Art");
        model.addAttribute("evenements",evenements);


        return "art";
    }

}

