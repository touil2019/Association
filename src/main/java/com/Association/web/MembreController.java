package com.Association.web;

import com.Association.dao.MembreRepository;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;
import com.Association.métier.IAssociationMetier;
import com.Association.métier.MembreDto;
import com.Association.security.RoleEnum;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        MembreDto membre = new MembreDto();
        model.addAttribute("membre", membre);
        model.addAttribute("roleEnum",RoleEnum.values());
        return "/inscription";
    }

    /**
     *
     * @param membreDto
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/membre/save", method = RequestMethod.POST)
    public String saveMembre(@Valid MembreDto membreDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/inscription";
        }
      Membre membre= new Membre();
        membre.setNom(membreDto.getNom());
        membre.setPrenom(membreDto.getPrenom());
        membre.setPseudo(membreDto.getPseudo());
        membre.setPassword(membreDto.getPassword());
        membre.setEmail(membreDto.getEmail());

        Set<RoleEnum> roles= new HashSet<>();
        switch (membreDto.getRole()){
            case "ACTIF":roles.add(RoleEnum.ROLE_ACTIF);break;
            case "BIENFAITEUR":roles.add(RoleEnum.ROLE_BIENFAITEUR);break;
            case "HONORAIRE":roles.add(RoleEnum.ROLE_HONORAIRE);break;
        }
        membre.setRoles(roles);
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
