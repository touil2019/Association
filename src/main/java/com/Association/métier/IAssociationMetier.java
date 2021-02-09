package com.Association.métier;

import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IAssociationMetier {



    Membre findByPseudo( String pseudo);

    Membre findByEmail(String email);

    Membre userConnected();

    List<Membre> listMembre(Long id);

    Optional<Evenement> findById(Long id);

    List<Evenement> findAllByThemeAndDateEvenementAfter(String theme, Date dateEvenement);

    List<Evenement> listeEvenementParTheme(String theme);

    List<Reservation> reservationsParMembre(Long id);

    Boolean membreParticipeDeja(Long idEvenement, Long idMembre);

    List<Membre> findAllByEnabledIsFalse();

}
