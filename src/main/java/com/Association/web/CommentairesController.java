package com.Association.web;


import com.Association.dao.CommentairesRepository;
import com.Association.dao.MembreRepository;
import com.Association.entities.Commentaires;
import com.Association.entities.Membre;
import com.Association.métier.IAssociationMetier;
import com.Association.security.RoleEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class CommentairesController {

    private static final Logger logger = LogManager.getLogger(CommentairesController.class);


    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private CommentairesRepository commentairesRepository;


    @PostMapping(value = "/home/{idMembre}/commentaires/save")
    public String ajouterCommentaire(Model model, @PathVariable(value = "idMembre") Long idMembre, @Valid Commentaires commentaires) {

        List<Membre> m = membreRepository.listMembre(idMembre);

        Membre membre = iAssociationMetier.userConnected();

        if (membre.getRoles().contains(RoleEnum.ROLE_ACTIF)) {

            model.addAttribute("Membre", membre);
            commentaires.setContenu("contenu");
            commentaires.setDate(new Date());
            commentaires.setMembre(membre);
            logger.info("Le Membre" + membre.getPseudo() + "à ajouter le commentaire" + commentaires.getId());
            commentairesRepository.save(commentaires);

        } else { return "/home"; }

        return  "redirect:/commentaires";
    }




}

