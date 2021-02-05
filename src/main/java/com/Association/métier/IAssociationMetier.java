package com.Association.métier;

import com.Association.entities.Commentaires;
import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IAssociationMetier {

  /*  List<Commentaires> listeCommentaireParMembre(@Param("idMembre") Long idMembre);*/

    Membre findByPseudo( String pseudo);

    Membre findByEmail(String email);

    Membre userConnected();

    List<Membre> listMembre(Long id);

    Optional<Evenement> findById(Long id);

    List<Evenement> findAllByThemeAndDateEvenementAfter(String theme, Date dateEvenement);

    List<Evenement> listeEvenementParTheme(String theme);
}
