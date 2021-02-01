package com.Association.web;

import com.Association.dao.MembreRepository;
import com.Association.entities.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class MembreController {

    @Autowired
    MembreRepository membreRepository;


    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/membre/create", method = RequestMethod.GET)
    public String creerMembre(Model model) {
        Membre membre = new Membre();
        model.addAttribute("membre", membre);
        return "CreerMembre";
    }

    /**
     *
     * @param model
     * @param membre
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/membre/save", method = RequestMethod.POST)
    public String saveMembre(Model model, @Valid Membre membre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "CreerMembre";
        }
        membreRepository.save(membre);

        return "redirect:/login";
    }


}
