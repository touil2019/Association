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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@Controller
public class MembreController {

    private static final Logger logger = LogManager.getLogger(MembreController.class);

    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    MembreRepository membreRepository;


    /**
     * Appel de la page d'accueil
     * @return retourne la page d'accueil
     */
    @GetMapping(value = "/home")
    public String home(){
        logger.info("Un visiteur veut accéder à la page d'accueil");
        return "/home"; }



    /**
     *Appel de la page d'inscription d'un nouveau membre
     * @param model
     * @return formulaire d'inscription
     */
    @RequestMapping(value = "/membre/create", method = RequestMethod.GET)
    public String creerMembre(Model model) {
        MembreDto membre = new MembreDto();
        logger.info("Un visiteur veut accéder au formulaire d'inscription");
        model.addAttribute("membre", membre);
        model.addAttribute("roleEnum",RoleEnum.values());
        return "/inscription";
    }

    /**
     *Sauvegarde d'un nouveau Membre et son rôle
     * @param membreDto
     * @param bindingResult
     * @return retourne la page login
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
        logger.info("L'utilisateur "+membreDto.getPseudo()+" a été ajouté a la DB");
        return "redirect:/login";
    }

    /**
     * Appel de la page Profil et qui retourne une liste de Reservation d'un membre et une liste de Membre
     * @param model
     * @return
     */
    @GetMapping(value="/profil")
    public String monProfil(Model model){

        Membre membre= iAssociationMetier.userConnected();
        List<Reservation> reservations = iAssociationMetier.reservationsParMembre(membre.getId());
        model.addAttribute("reservations",reservations);


        List<Membre> membres = iAssociationMetier.findAllByEnabledIsFalse();
        List<MembreDto> membreDtos= new ArrayList<>();
        for (Membre m: membres) {
            membreDtos.add(new MembreDto(m));
        }
        model.addAttribute("membres",membreDtos);
        logger.info("Un visiteur veut accéder à son profil");
        return "profil";
    }

    /**
     * Validation d'un Membre suite à la création de son compte
     * @param id identifiant du membre
     * @return retourne la page profil
     */
    @GetMapping(value="/membre/{id}/valider")
    public String validerMembre(@PathVariable(name="id")Long id){
        Membre membre= iAssociationMetier.userConnected();
        if(membre.getRoles().contains(RoleEnum.ROLE_ADMIN)){
            Optional<Membre> m= membreRepository.findById(id);
            if(m.isPresent()){
                Membre membreAValider= m.get();
                membreAValider.setEnabled(true);
                membreRepository.save(membreAValider);
                logger.info("Le profil du membre "+m.get()+" a été validé");
            }

        }
      return "redirect:/profil";
    }

}
