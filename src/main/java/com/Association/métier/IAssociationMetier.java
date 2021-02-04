package com.Association.métier;

import com.Association.entities.Commentaires;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAssociationMetier {

    List<Commentaires> listeCommentaireParMembre(@Param("idMembre") Long idMembre);

    Membre findByPseudo(@Param("pseudo") String pseudo);

    Membre findByEmail(@Param("email") String email);

    List<Evenement> listeEvenementThemeArt(@Param("Art") String theme);

    List<Evenement> listeEvenementThemeMusique(@Param("Musique") String theme);

    List<Evenement> listeEvenementThemeConference(@Param("Conference") String theme);

    Membre userConnected();
}
