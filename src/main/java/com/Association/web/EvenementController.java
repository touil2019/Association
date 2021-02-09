package com.Association.web;

import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Evenement;
import com.Association.entities.Reservation;
import com.Association.métier.EvenementDto;
import com.Association.métier.EventParticipantsDto;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EvenementController {

    private static final Logger logger = LogManager.getLogger(EvenementController.class);

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
        List<EventParticipantsDto> eventParticipantsDtoList= new ArrayList<>();
        List<Evenement> evenements= evenementCulturelRepository.listeEvenementParTheme("Art");
        for (Evenement evenement: evenements) {
            List<Reservation> reservations= reservationRepository.findAllByEvenement_Id(evenement.getId());
            EventParticipantsDto eventParticipantsDto= new EventParticipantsDto(evenement,reservations);
            eventParticipantsDtoList.add(eventParticipantsDto);
        }
        model.addAttribute("eventDtoList",eventParticipantsDtoList);


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

    @RequestMapping(value = "/evenement/creer", method = RequestMethod.GET)
    public String creerEvent(Model model) {

        EvenementDto evenement = new EvenementDto();

        model.addAttribute("evenement", evenement);

        return "formNewEvent";
    }

    @RequestMapping(value = "/evenement/save", method = RequestMethod.POST)
    public String saveEvent(@Valid EvenementDto evenementDto, BindingResult bindingResult) throws ParseException {

        if (bindingResult.hasErrors()) {
            return "redirect:/evenement/creer";
        }
        Evenement evenement=new Evenement(evenementDto.getTheme(),evenementDto.getNom(),
                evenementDto.getNombreParticipantMax(),evenementDto.getDescription());
        Date date= new SimpleDateFormat("yyyy-MM-dd").parse(evenementDto.getDateEvenement());
        evenement.setDateEvenement(date);
        evenementCulturelRepository.save(evenement);
        switch (evenement.getTheme()){
            case "Art": return "redirect:/art";
            case "Culture": return "redirect:/culture";
            case "Conference": return "redirect:/conference";
            default: return "redirect:/home";

        }


    }


}

