package com.Association.web;

import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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


    @GetMapping(value = "/culture/{id}")
    public Evenement myEventCulture(@PathVariable("id")Long id)
    {
        return evenementCulturelRepository.findById(id).get() ; }

    @GetMapping(value = "/art/{id}")
    public Evenement myEventArt(@PathVariable("id")Long id)
    {
        return evenementCulturelRepository.findById(id).get() ; }


    @GetMapping(value="/conference/{id}")
    public Evenement myEventConference(@PathVariable("id")Long id){
        return evenementCulturelRepository.findById(id).get();    }



    @GetMapping(value = "/art")
    public String eventArt(Model model){

        List<Evenement> evenements= evenementCulturelRepository.listeEvenementParTheme("Art");
        model.addAttribute("evenements",evenements);


        return "art";
    }

    @GetMapping(value = "/culture")
    public String eventCulture(Model model){

        List<Evenement> eventCulture = evenementCulturelRepository.listeEvenementParTheme("Culture");
        model.addAttribute("eventCulture", eventCulture);


        return "culture";
    }

    @GetMapping(value="/conference")
    public String eventConference(Model model){
        List<Evenement> eventConference = evenementCulturelRepository.listeEvenementParTheme("Conference");
        model.addAttribute("eventConference",eventConference);

        return "conference";
    }

    @RequestMapping(value = "/formNewEvent/create", method = RequestMethod.GET)
    public String creerEvent(Model model) {

        Evenement evenement = new Evenement();

        model.addAttribute("evenement", evenement);

        return "/formNewEvent";
    }

    @RequestMapping(value = "/evenement/save", method = RequestMethod.POST)
    public String saveEvent(@Valid Evenement evenement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/formNewEvent";
        }
        evenement.setTheme("");
        evenement.setNom("");
        evenement.setDateEvenement(new Date());
        evenement.setNombreParticipant(10);
        evenement.getDescription();
        evenementCulturelRepository.save(evenement);

        return "redirect:/home";
    }

    @GetMapping(value="/formNewEvent")
    public String newEvent(Model model){

        return "formNewEvent";
    }
}

