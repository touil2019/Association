package com.Association.web;

import com.Association.dao.MembreRepository;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MembreController {

    private static final Logger logger = LogManager.getLogger(MembreController.class);

    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    MembreRepository membreRepository;




    @GetMapping(value = "/home")
    public String home(){
        return "/home"; }



    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/membre/create", method = RequestMethod.GET)
    public String creerMembre(Model model) {
        Membre membre = new Membre();
        model.addAttribute("membre", membre);
        return "/inscription";
    }

    /**
     *
     * @param membre
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/membre/save", method = RequestMethod.POST)
    public String saveMembre(@Valid Membre membre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/inscription";
        }
        membreRepository.save(membre);

        return "redirect:/login";
    }

    @GetMapping(value="/profil")
    public String monProfil(Model model){

        Membre membre= iAssociationMetier.userConnected();
        List<Reservation> reservations = iAssociationMetier.reservationsParMembre(membre.getId());
        model.addAttribute("reservations",reservations);

        return "profil";
    }

}
