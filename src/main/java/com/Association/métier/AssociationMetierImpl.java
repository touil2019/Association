package com.Association.métier;

import com.Association.dao.CommentairesRepository;
import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;
import com.Association.entities.Commentaires;

import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociationMetierImpl implements IAssociationMetier {

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private CommentairesRepository commentairesRepository;

    @Autowired
    private EvenementCulturelRepository evenementCulturelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Commentaires> listeCommentaireParMembre(Long idMembre) {
        return commentairesRepository.listeCommentaireParMembre(idMembre);
    }

    @Override
    public Membre findByPseudo(String pseudo) {
        return membreRepository.findByPseudo(pseudo);
    }

    @Override
    public Membre findByEmail(String email) {
        return membreRepository.findByEmail(email);
    }

    @Override
    public List<Evenement> listeEvenementThemeArt(String theme) {
        return evenementCulturelRepository.listeEvenementThemeArt(theme);
    }

    @Override
    public List<Evenement> listeEvenementThemeMusique(String theme) {
        return evenementCulturelRepository.listeEvenementThemeMusique(theme);
    }

    @Override
    public List<Evenement> listeEvenementThemeConference(String theme) {
        return evenementCulturelRepository.listeEvenementThemeConference(theme);
    }

    @Override
    public Membre userConnected() {
        Membre membre=(Membre) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return membre;
    }
}
